package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.Message;
 
 
public interface MessageDao {
     
    public List<Message> getMessages();
    
    Message findById(String id);
    
    List<Message> findByAccount(String account);
     
    void save(Message message);
 
    List<Message> findAllMessages();
    
    List<Message> findBySentiment(Integer sentiment);
    
    void updateSentiment(Message message, Integer sentiment);
 
}