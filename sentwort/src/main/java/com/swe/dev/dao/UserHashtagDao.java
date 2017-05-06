package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.User;
import com.swe.dev.model.UserHashtag;
import com.swe.dev.model.UserHashtagId;
 
 
public interface UserHashtagDao {
     
    List<UserHashtag> findByUserId(Integer userid);
    
    UserHashtag findById(UserHashtagId id);
     
    void save(UserHashtag userhashtag);
     
    public List<UserHashtag> findAll();
 
}