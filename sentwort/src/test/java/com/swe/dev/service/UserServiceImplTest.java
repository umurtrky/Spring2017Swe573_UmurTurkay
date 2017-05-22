package com.swe.dev.service;

import static org.mockito.Matchers.anyInt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.swe.dev.dao.MessageDao;
import com.swe.dev.dao.UserDao;
import com.swe.dev.model.Message;
import com.swe.dev.model.User;

public class UserServiceImplTest {

	@Mock
    UserDao dao;
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Spy
	List<User> users = new ArrayList<User>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		users = getUserList();
	}
     
	@Test
    public void findById() {
		User us = users.get(0);
        when(dao.findById(anyInt())).thenReturn(us);
        Assert.assertEquals(userService.findById(us.getId()),us);
    }
 
	@Test
    public void findAllUsers() {
		when(dao.findAllUsers()).thenReturn(users);
        Assert.assertEquals(userService.findAllUsers(), users);
    }
    
    public List<User> getUserList(){
    	User u1 = new User();
    	u1.setCreateDate(new Date());
    	u1.setId(1);
    	u1.setPassword("pass123");
    	u1.setSecretanswer("answer");
    	u1.setSecretquestionid(1);
    	u1.setUpdateDate(null);
    	u1.setUsername("u123");
    	
    	User u2 = new User();
    	u2.setCreateDate(new Date());
    	u2.setId(1);
    	u2.setPassword("pass124");
    	u2.setSecretanswer("answer2");
    	u2.setSecretquestionid(1);
    	u2.setUpdateDate(null);
    	u2.setUsername("u124");
    	
    	users.add(u1);
    	users.add(u2);
    	
    	return users;
    }
}
