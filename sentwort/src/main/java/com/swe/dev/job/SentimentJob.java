package com.swe.dev.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import com.swe.dev.model.Message;
import com.swe.dev.service.MessageService;
import com.swe.dev.service.SentimentService;

public class SentimentJob implements Job {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	SentimentService sentimentService;
	
	@Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {

      System.out.println("Executing Sentiment Job");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		List<Message> nonAnalyzedMessages = new ArrayList<Message>();
		nonAnalyzedMessages = messageService.findBySentiment(-1);
		if(nonAnalyzedMessages.size() > 0){
			sentimentService.analyze(nonAnalyzedMessages);
		}
    }

}
