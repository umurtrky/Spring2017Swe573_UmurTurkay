package com.swe.dev.service;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.swe.dev.dao.HashtagDao;
import com.swe.dev.model.Hashtag;

public class HashtagServiceImplTest {
	
	@Mock
	HashtagDao dao;
	
	@InjectMocks
	HashtagServiceImpl hashtagService;
	
	@Mock
	SessionFactory sessionFactory;
	
	@Spy
	List<Hashtag> hashtags = new ArrayList<Hashtag>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		hashtags = getHashtagList();
	}
	
	@Test
	public void findByUser(){
		
	}
	
	@Test
	public void findByMessageId(){
		
	}
	
	public List<Hashtag> getHashtagList(){
		Hashtag hashtag1 = new Hashtag();
		hashtag1.setCreatedate(new Date());
		hashtag1.setHashtagname("goal");
		hashtag1.setId(1);
		hashtag1.setIsactive(0);
		hashtag1.setStartdate(new Date());
		hashtag1.setStopdate(null);
		
		Hashtag hashtag2 = new Hashtag();
		hashtag2.setCreatedate(new Date());
		hashtag2.setHashtagname("trump");
		hashtag2.setId(2);
		hashtag2.setIsactive(0);
		hashtag2.setStartdate(new Date());
		hashtag2.setStopdate(null);
		
		hashtags.add(hashtag1);
		hashtags.add(hashtag2);
		
		return hashtags;
	}

}
