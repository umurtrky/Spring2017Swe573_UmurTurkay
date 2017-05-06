package com.swe.dev.service;

import java.util.List;

import com.swe.dev.model.Hashtag;
import com.swe.dev.model.Message;

public interface TwitterService {
	
	public List<Message> getTweets(List<Hashtag> hashtags);
	
	public List<Message> retrieveTweets();

}
