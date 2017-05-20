package com.swe.dev.service;

import java.util.Date;
import java.util.List;

import com.swe.dev.model.Message;
import com.swe.dev.model.SentimentReport;

public interface SentimentService {
	
	void analyze(List<Message> nonAnalyzedMessages);
	
	List<SentimentReport> getReport(List<Integer> hashtagIds);
	List<SentimentReport> getFilteredReport(List<Integer> hashtagIds, Date startdate, Date enddate, List<Integer> sentiment);

}
