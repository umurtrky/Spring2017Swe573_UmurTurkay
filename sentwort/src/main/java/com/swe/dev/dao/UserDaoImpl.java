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
 
 
 
@Repository("userDao")
@Transactional
@Component
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
 
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
     
    public User findById(int id) {
        User user = getByKey(id);
//        if(user!=null){
//            Hibernate.initialize(user.getUserProfiles());
//        }
        return user;
    }
 
    public User findByUsername(String username) {
        logger.info("username : {}", username);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User)crit.uniqueResult();
//        if(user!=null){
//            Hibernate.initialize(user.getUserProfiles());
//        }
        return user;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllUsers() {
    	return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
 
    public void save(User user) {
        persist(user);
    }
 
    public void deleteByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User)crit.uniqueResult();
        delete(user);
    }
 
}
