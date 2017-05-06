package com.swe.dev.converter;

import java.util.Date;

import com.swe.dev.model.Message;

import twitter4j.Status;

public class StatusMessageConverter {

	public static Message convert(Status tweet){
		Message message = new Message();
		message.setId(String.valueOf(tweet.getId()));
		message.setAccount(tweet.getUser().getScreenName());
        message.setLinks(tweet.getURLEntities().toString());
        message.setMessage(tweet.getText());
        message.setSentiment(-1);
        message.setSharedate(tweet.getCreatedAt());
        message.setCreatedate(new Date());
        
        return message;
	}
}
