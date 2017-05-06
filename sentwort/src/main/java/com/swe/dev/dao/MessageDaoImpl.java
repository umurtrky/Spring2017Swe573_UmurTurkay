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
import com.swe.dev.model.User;
 
 
@Repository("messageDao") 
public class MessageDaoImpl extends AbstractDao<String, Message> implements MessageDao {
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> getMessages() {
        return createEntityCriteria().list();
    }
    
    @Override
    public Message findById(String id) {
    	Message message = getByKey(id);
    	return message;
    }
 
    @Override
    public List<Message> findByAccount(String account) {
    	logger.info("account : {}", account);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("account", account));
        List<Message> message = (List<Message>)crit.list();

        return message;
    }
    
    @Override
    public void save(Message message) {
    	persist(message);
    }
    
    @Override
    public List<Message> findAllMessages() {
    	Criteria criteria = createEntityCriteria().addOrder(Order.desc("createdate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Message> messages = (List<Message>) criteria.list();
         

        return messages;
    }
    
    @Override
    public List<Message> findBySentiment(Integer sentiment){
    	logger.info("sentiment : {}", sentiment);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("sentiment", sentiment));
        List<Message> messages = (List<Message>)crit.list();

        return messages;
    }
    
    @Override
    public void updateSentiment(Message message, Integer sentiment){
    	message.setSentiment(sentiment);
    	update(message);
    }
}
