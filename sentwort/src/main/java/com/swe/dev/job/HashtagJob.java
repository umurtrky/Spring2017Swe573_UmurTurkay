package com.swe.dev.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.swe.dev.dao.HashtagDao;
import com.swe.dev.model.Hashtag;
import com.swe.dev.service.TwitterService;

public class HashtagJob implements Job {
	

	@Autowired
	HashtagDao hashtagDao;
	
	@Autowired
	TwitterService twitterService;
	
	@Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {

        System.out.println("Executing Hashtag Job");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		List<Hashtag> actives = new ArrayList<Hashtag>();
		actives = hashtagDao.findActiveHashtags();
		if(actives.size() > 0){
			twitterService.getTweets(actives);
		}
    }

}
