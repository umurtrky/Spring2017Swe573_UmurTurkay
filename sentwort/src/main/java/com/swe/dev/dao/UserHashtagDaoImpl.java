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

import com.swe.dev.model.User;
import com.swe.dev.model.UserHashtag;
import com.swe.dev.model.UserHashtagId;
 
 
 
@Repository("userHashtagDao")
@Transactional
@Component
public class UserHashtagDaoImpl extends AbstractDao<UserHashtagId, UserHashtag> implements UserHashtagDao {
 
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
 
    public List<UserHashtag> findByUserId(Integer userid) {
        logger.info("userid : {}", userid);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userid", userid));
//        if(user!=null){
//            Hibernate.initialize(user.getUserProfiles());
//        }
        return (List<UserHashtag>)crit.list();
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<UserHashtag> findAll() {
    	return sessionFactory.getCurrentSession().createCriteria(UserHashtag.class).list();
    }
 
    public void save(UserHashtag userhashtag) {
        persist(userhashtag);
    }
    
    public UserHashtag findById(UserHashtagId id){
    	UserHashtag userHashtag = getByKey(id);
    	return userHashtag;
    }
 
}
