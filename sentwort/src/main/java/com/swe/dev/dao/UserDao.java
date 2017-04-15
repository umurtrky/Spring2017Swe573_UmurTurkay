package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.User;
 
 
public interface UserDao {
 
    User findById(int id);
     
    User findByUsername(String username);
     
    void save(User user);
     
    void deleteByUsername(String username);
     
    public List<User> findAllUsers();
 
}