package com.swe.dev.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.swe.dev.dao.HashtagDao;
import com.swe.dev.dao.UserDaoImpl;
import com.swe.dev.dao.UserHashtagDao;
import com.swe.dev.model.Hashtag;
import com.swe.dev.model.HashtagReport;
import com.swe.dev.model.Message;
import com.swe.dev.model.UserHashtag;
import com.swe.dev.model.UserHashtagId;
import com.swe.dev.service.HashtagService;
import com.swe.dev.service.MessageService;
import com.swe.dev.service.SentimentService;
import com.swe.dev.service.TwitterService;
import com.swe.dev.service.UserService;



@Controller
@RequestMapping("/hashtag/")
public class HashtagController {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired 
    private HashtagDao hashtagDao;
	
	@Autowired 
    private UserHashtagDao userhashtagDao;
	
	@Autowired
    MessageService messageService;
    
    @Autowired
	private HashtagService hashtagService;
    
    @Autowired
	SentimentService sentimentService;
	
	@Autowired
    UserService userService;
	
	@Autowired
    private TwitterService twitterService;
	
	@Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
    
    @RequestMapping(value = "/hashtagManagement", method = RequestMethod.GET)
    public String index(ModelMap model) {
    	if (isCurrentAuthenticationAnonymous()) {
            return "signin";
        }
    	else{
    		model.addAttribute("loggedinuser", getPrincipal());
    		return "hashtag/hashtagManagement";
    	}
    }
    
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public @ResponseBody List<Hashtag> read() {
        //return hashtagDao.findAllHashtags();
    	return hashtagService.findByUser(getPrincipal());
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Hashtag create(@RequestBody ModelMap model) {
    	try{
    		Hashtag target = hashtagDao.findByName((String)model.get("hashtagname"));
    		if(target == null){
    		
		    	//yyyy-MM-dd HH:mm:ss.SSS
		    	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddXmm:HH:mm:ss.SSS");
    			target = new Hashtag();
		        target.setHashtagname((String)model.get("hashtagname"));
		        target.setIsactive(0);
		        target.setCreatedate(DatatypeConverter.parseDateTime(model.get("createdate").toString()).getTime());
		        target.setStartdate(DatatypeConverter.parseDateTime(model.get("startdate").toString()).getTime());
		        target.setStopdate(null);        
	        
		        hashtagDao.save(target);
    		}
    		Integer userid = userService.findByUsername(getPrincipal()).getId();
    		UserHashtag userhashtag = userhashtagDao.findById(new UserHashtagId(userid, target.getId()));
    		if(userhashtag == null){
    			userhashtag = new UserHashtag();
		        userhashtag.setId(new UserHashtagId(userid, target.getId()));
		        userhashtag.setCreatedate(new Date());
		        userhashtagDao.save(userhashtag);
    		}
    		else {
    			model.addAttribute("hashtagAlreadyExists", (String)model.get("hashtagname")  + " already exits.");
    		}
	        
	        return target;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return null;
    	}
        
    }
    
    @RequestMapping(value = "/startListening", method = RequestMethod.POST)
    public @ResponseBody Hashtag startListening(@RequestBody ModelMap model) {
    	try{
    		Hashtag target = hashtagDao.findById(Integer.parseInt(model.get("id").toString()));
    		if(target != null){      
	        
		        hashtagDao.updateActive(target, 1);
    		}
	        
	        return target;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return null;
    	}
        
    }
    
    @RequestMapping(value = "/stopListening", method = RequestMethod.POST)
    public @ResponseBody Hashtag stopListening(@RequestBody ModelMap model) {
    	try{
    		Hashtag target = hashtagDao.findById(Integer.parseInt(model.get("id").toString()));
    		if(target != null){        
		        hashtagDao.updateActive(target, 0);
    		}
	        
	        return target;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return null;
    	}
        
    }
    
    @RequestMapping(value = "/destroy", method = RequestMethod.POST)
    public @ResponseBody Hashtag destroy(@RequestBody Map<String, Object> model) {
    	Hashtag target = new Hashtag();
        
        target.setHashtagname((String)model.get("hashtagname"));
        
        hashtagDao.deleteByName(target.getHashtagname());
        
        return target;
    }
    
    @RequestMapping(value = "/hashtagReport", method = RequestMethod.GET)
    public String sentimentAnalysis(ModelMap model) {
    	if (isCurrentAuthenticationAnonymous()) {
            return "signin";
        }
    	else{
    		model.addAttribute("loggedinuser", getPrincipal());
        	return "hashtag/hashtagReport";
    	}
    }
    
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public @ResponseBody List<HashtagReport> report(ModelMap model) {
    	Integer userid = userService.findByUsername(getPrincipal()).getId();
    	return hashtagService.getReport(userid);
    }
    
    
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
     

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
