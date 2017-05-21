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

import com.swe.dev.model.SecretQuestion;
import com.swe.dev.model.User;
 
 
 
@Repository("secretQuestionDao")
@Transactional
@Component
public class SecretQuestionDaoImpl extends AbstractDao<Integer, SecretQuestion> implements SecretQuestionDao {
 
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
     
    public SecretQuestion findById(int id) {
    	SecretQuestion secretQuestion = getByKey(id);
        return secretQuestion;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<SecretQuestion> findAllQuestions() {
    	return sessionFactory.getCurrentSession().createCriteria(SecretQuestion.class).list();
    }
 
}
