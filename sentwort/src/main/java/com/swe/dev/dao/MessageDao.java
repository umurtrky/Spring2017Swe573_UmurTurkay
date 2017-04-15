package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.Message;
 
 
public interface MessageDao {
     
    public List<Message> getMessages();
 
}