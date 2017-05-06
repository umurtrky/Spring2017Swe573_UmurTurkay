package com.swe.dev.service;

import java.util.List;

import com.swe.dev.model.Message;

public interface SentimentService {
	
	void analyze(List<Message> nonAnalyzedMessages);

}
