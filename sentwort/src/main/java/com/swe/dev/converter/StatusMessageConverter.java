package com.swe.dev.converter;

import com.swe.dev.model.Message;

import twitter4j.Status;

public class StatusMessageConverter {

	public static Message convert(Status tweet){
		Message message = new Message();
		message.setAccount(tweet.getUser().getScreenName());
        message.setLinks(tweet.getURLEntities().toString());
        message.setMessage(tweet.getText());
        message.setSentiment(0);
        message.setSharedate(tweet.getCreatedAt());
        
        return message;
	}
}
