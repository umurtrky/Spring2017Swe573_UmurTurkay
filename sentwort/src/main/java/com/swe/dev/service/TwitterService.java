package com.swe.dev.service;

import java.util.List;

import com.swe.dev.model.Message;

public interface TwitterService {
	
	public List<Message> getTweets();

}
