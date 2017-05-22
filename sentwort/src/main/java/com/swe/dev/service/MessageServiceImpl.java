package com.swe.dev.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swe.dev.dao.MessageDao;
import com.swe.dev.dao.UserDao;
import com.swe.dev.dao.UserDaoImpl;
import com.swe.dev.model.Message;
 
 
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService{
	
	static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private MessageDao dao;
     
    public Message findById(String id) {
        return dao.findById(id);
    }
 
    public List<Message> findByAccount(String account) {
    	List<Message> messages = dao.findByAccount(account);
        return messages;
    }
 
    public void save(Message message) {
    	Message existingMessage = findById(message.getId());
    	if(existingMessage == null){
    		dao.save(message);
    	}
    	else {
    		logger.error("Message with id: " + message.getId() + " already exists.");
    	}
    }
 
    public List<Message> findAllMessages() {
        return dao.findAllMessages();
    }
    
    public List<Message> findByHashtagId(List<Integer> hashtagIds){
    	List<Message> messages = new ArrayList<Message>();
		
		Query<Message> query = sessionFactory.getCurrentSession().createQuery("SELECT m from Message m, MessageHashtag mht "
				+ "where mht.id.messageid=m.id and mht.id.hashtagid in :hashtagids order by m.sharedate desc");
		query.setParameter("hashtagids", hashtagIds);
		
		messages.addAll(query.list());
		return messages;
    }
    
    public List<Message> findBySentiment(Integer sentiment){
    	List<Message> messages = dao.findBySentiment(sentiment);
        return messages;
    }
    
    public void updateSentiment(Message message, Integer sentiment){
    	dao.updateSentiment(message, sentiment);
    }
    
    public Long getNumOfMessagesBySentiment(Integer sentiment, Integer userid){
    	Long number = Long.valueOf("0");
		Query query = null;
//		select message from message m join messagehashtag mt on m.id = mt.messageid
//				 join userhashtag ut on ut.hashtagid = mt.hashtagid where ut.userid = 1
		query = sessionFactory.getCurrentSession().createQuery("SELECT count(m.id) from Message m, MessageHashtag mt, UserHashtag ut "
				+ "where m.id = mt.id.messageid and mt.id.hashtagid = ut.id.hashtagid and ut.id.userid = :userid");
		if(sentiment != 1){
			query = sessionFactory.getCurrentSession().createQuery("SELECT count(m.id) from Message m, MessageHashtag mt, UserHashtag ut "
					+ "where m.id = mt.id.messageid and mt.id.hashtagid = ut.id.hashtagid and ut.id.userid = :userid and m.sentiment = :sentiment");
			query.setParameter("sentiment", sentiment);
		}
		query.setParameter("userid", userid);
		
		number = (Long)query.uniqueResult();
		return number;
    }
     
}