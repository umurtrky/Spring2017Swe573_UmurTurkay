package com.swe.dev.service;

import java.util.List;

import com.swe.dev.model.Hashtag;
import com.swe.dev.model.HashtagReport;

public interface HashtagService {
	
	List<Hashtag> findByUser(String username);
	List<Hashtag> findByMessageId(String messageid);
	List<HashtagReport> getReport(Integer userid);
	Long getNumberOfHashtags(int isactive, Integer userid);
	List<Hashtag> findAllHashtags();
}
