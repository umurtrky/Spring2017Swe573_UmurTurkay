package com.swe.dev.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.swe.dev.model.Hashtag;
 
 
 
@Repository("hashtagDao")
@Transactional
@Component
public class HashtagDaoImpl extends AbstractDao<Integer, Hashtag> implements HashtagDao {
 
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;
     
    public Hashtag findById(int id) {
    	Hashtag hashtag = getByKey(id);
        return hashtag;
    }
 
    public Hashtag findByName(String name) {
        logger.info("hashtag name : {}", name);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("hashtagname", name));
        Hashtag hashtag = (Hashtag)crit.uniqueResult();

        return hashtag;
    }
    
    public List<Hashtag> findActiveHashtags() {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("isactive", 1));
        List<Hashtag> hashtags = (List<Hashtag>)crit.list();

        return hashtags;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Hashtag> findAllHashtags() {
    	return sessionFactory.getCurrentSession().createCriteria(Hashtag.class).list();
    }
 
    public void save(Hashtag hashtag) {
        persist(hashtag);
    }
 
    public void deleteByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("hashtagname", name));
        Hashtag hashtag = (Hashtag)crit.uniqueResult();
        delete(hashtag);
    }
    
    public void updateActive(Hashtag hashtag, Integer isactive){
    	hashtag.setIsactive(isactive);
    	if(isactive == 0){
    		hashtag.setStopdate(new Date());
    	}
    	else{
    		hashtag.setStartdate(new Date());
    	}
    	update(hashtag);
    }
 
}
