package com.swe.dev.service;

import java.util.List;

import com.swe.dev.model.Message;
 
 
public interface MessageService {
     
    Message findById(String id);
     
    List<Message> findByAccount(String account);
     
    void save(Message message);
 
    List<Message> findAllMessages(); 
 
    List<Message> findByHashtagId(List<Integer> hashtagIds);
    
    List<Message> findBySentiment(Integer sentiment);
    
    void updateSentiment(Message message, Integer sentiment);
    
    Long getNumOfMessagesBySentiment(Integer sentiment);
}
