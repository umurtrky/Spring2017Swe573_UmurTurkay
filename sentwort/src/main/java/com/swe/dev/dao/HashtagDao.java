package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.Hashtag;
 
 
public interface HashtagDao {
 
	Hashtag findById(int id);
     
	Hashtag findByName(String name);
	
	List<Hashtag> findActiveHashtags();
     
    void save(Hashtag hashtag);
     
    void deleteByName(String name);
     
    public List<Hashtag> findAllHashtags();
    
    void updateActive(Hashtag hashtag, Integer isactive);
 
}