package com.swe.dev.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.swe.dev.dao.UserDao;
import com.swe.dev.model.Hashtag;
import com.swe.dev.model.Message;
import com.swe.dev.model.User;
import com.swe.dev.service.HashtagService;
import com.swe.dev.service.MessageService;
import com.swe.dev.service.TwitterService;
import com.swe.dev.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {
 
    @Autowired
    UserService userService;
    
    @Autowired
    private TwitterService twitterService;
    
    @Autowired
    MessageService messageService;
    
    @Autowired
	private HashtagService hashtagService;
    
    @Autowired
    UserDao user;
     
//    @Autowired
//    UserProfileService userProfileService;
     
    @Autowired
    MessageSource messageSource;
 
    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
     
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
     
     
    @RequestMapping(value = {"/", "/report"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
    	if (isCurrentAuthenticationAnonymous()) {
            return "signin";
        }
    	else{
    		model.addAttribute("loggedinuser", getPrincipal());
    		Long numofhashtags = hashtagService.getNumberOfHashtags(-1);
        	Long numofactivehashtags = hashtagService.getNumberOfHashtags(1);
        	Long numofpassivehashtags = numofhashtags - numofactivehashtags;
        	
        	Long numOfMessages = messageService.getNumOfMessagesBySentiment(1);
        	Long numOfPositiveMessages = messageService.getNumOfMessagesBySentiment(4);
        	Long numOfNegativeMessages = messageService.getNumOfMessagesBySentiment(0);
        	Long numOfNeutralMessages = messageService.getNumOfMessagesBySentiment(2);
        	Long numOfAnalyzedMessages = numOfPositiveMessages + numOfNegativeMessages + numOfNeutralMessages;
        	
        	model.addAttribute("numofhashtags", numofhashtags);
        	model.addAttribute("numofactivehashtags", numofactivehashtags);
        	model.addAttribute("numofpassivehashtags", numofpassivehashtags);
        	model.addAttribute("numOfMessages", numOfMessages);
        	model.addAttribute("numOfPositiveMessages", numOfPositiveMessages);
        	model.addAttribute("numOfNegativeMessages", numOfNegativeMessages);
        	model.addAttribute("numOfNeutralMessages", numOfNeutralMessages);
        	model.addAttribute("numOfAnalyzedMessages", numOfAnalyzedMessages);
        	return "index";
    	}
    }
    
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(ModelMap model) {
    	Long numofhashtags = hashtagService.getNumberOfHashtags(-1);
    	Long numofactivehashtags = hashtagService.getNumberOfHashtags(1);
    	Long numofpassivehashtags = numofhashtags - numofactivehashtags;
    	
    	Long numOfMessages = messageService.getNumOfMessagesBySentiment(1);
    	Long numOfPositiveMessages = messageService.getNumOfMessagesBySentiment(4);
    	Long numOfNegativeMessages = messageService.getNumOfMessagesBySentiment(0);
    	Long numOfNeutralMessages = messageService.getNumOfMessagesBySentiment(2);
    	Long numOfNonAnalyzedMessages = numOfMessages - (numOfPositiveMessages + numOfNegativeMessages + numOfNeutralMessages);
    	
    	model.addAttribute("numofhashtags", numofhashtags);
    	model.addAttribute("numofactivehashtags", numofactivehashtags);
    	model.addAttribute("numofpassivehashtags", numofpassivehashtags);
    	model.addAttribute("numOfMessages", numOfMessages);
    	model.addAttribute("numOfPositiveMessages", numOfPositiveMessages);
    	model.addAttribute("numOfNegativeMessages", numOfNegativeMessages);
    	model.addAttribute("numOfNeutralMessages", numOfNeutralMessages);
    	model.addAttribute("numOfNonAnalyzedMessages", numOfNonAnalyzedMessages);
    	return "";
    }
    
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public @ResponseBody List<Message> messages() {
    	//twitterService.getTweets();
    	//return messageService.findAllMessages();
    	List<Hashtag> hashtagsOfUser = hashtagService.findByUser(getPrincipal());
    	List<Integer> hashtagIds = new ArrayList<Integer>();
    	for(Hashtag hashtag : hashtagsOfUser){
    		hashtagIds.add(hashtag.getId());
    	}
    	return messageService.findByHashtagId(hashtagIds);
    }
    
    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public @ResponseBody List<Message> tweets() {
    	return twitterService.retrieveTweets();
    }
    
    @RequestMapping(value = "/sentiment", method = RequestMethod.GET)
    public String sentiment(ModelMap model) {
    	if (isCurrentAuthenticationAnonymous()) {
            return "signin";
        }
    	else{
    		model.addAttribute("loggedinuser", getPrincipal());
        	return "sentimentReport";
    	}
    }
 
    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        user.setCreateDate(new Date());
        model.addAttribute("user", user);
        model.addAttribute("loggedinuser", getPrincipal());
        return "signup";
    }
 
    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(User user, BindingResult result,
            ModelMap model) {
 
        if (result.hasErrors()) {
            return "signup";
        }
 

        if(!userService.isUsernameUnique(user.getId(), user.getUsername())){
            FieldError usernameError =new FieldError("user","username",messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
            result.addError(usernameError);
            return "signup";
        }
         
        user.setCreateDate(new Date());
        userService.saveUser(user);
 
        model.addAttribute("success", user.getUsername() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "signin";
    }
 
 

    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }
     
 
    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable String username) {
 
        if (result.hasErrors()) {
            return "registration";
        }
 
 
        userService.updateUser(user);
 
        model.addAttribute("success", user.getUsername() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }
 
     

    @RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/list";
    }
    
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }
 

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "signin";
        } else {
            return "redirect:/sentimentReport";  
        }
    }
 

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
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
 
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
 
}
