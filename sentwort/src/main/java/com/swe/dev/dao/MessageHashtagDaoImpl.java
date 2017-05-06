package com.swe.dev.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.swe.dev.model.Message;
import com.swe.dev.model.MessageHashtag;
import com.swe.dev.model.MessageHashtagId;
import com.swe.dev.model.User;
import com.swe.dev.model.UserHashtag;
 
 
 
@Repository("messageHashtagDao")
@Transactional
@Component
public class MessageHashtagDaoImpl extends AbstractDao<MessageHashtagId, MessageHashtag> implements MessageHashtagDao {
 
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
 
    public MessageHashtag findById(MessageHashtagId messagehashtagid) {
    	MessageHashtag messageHashtag = getByKey(messagehashtagid);
    	return messageHashtag;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<MessageHashtag> findAll() {
    	return sessionFactory.getCurrentSession().createCriteria(MessageHashtag.class).list();
    }
 
    public void save(MessageHashtag messagehashtag) {
    	MessageHashtag existingMessageHashtag = findById(messagehashtag.getId());
    	if(existingMessageHashtag == null){
    		persist(messagehashtag);
    	}
    	else {
    		logger.error("Message with id: " + messagehashtag.getId() + " already exists.");
    	}
    }
 
}
