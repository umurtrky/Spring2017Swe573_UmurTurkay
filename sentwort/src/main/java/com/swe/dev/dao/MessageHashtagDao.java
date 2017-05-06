package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.MessageHashtag;
import com.swe.dev.model.MessageHashtagId;
 
 
public interface MessageHashtagDao {
     
    MessageHashtag findById(MessageHashtagId id);
     
    void save(MessageHashtag messagehashtag);
     
    public List<MessageHashtag> findAll();
 
}