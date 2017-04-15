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

import com.swe.dev.model.AppConfig;
import com.swe.dev.model.User;
 
 
 
@Transactional
@Component
public class AppConfigDaoImpl implements AppConfigDao {
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
 
	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<AppConfig> getConfig() {
        return sessionFactory.getCurrentSession().createCriteria(AppConfig.class).list();
    } 
    
    public AppConfig findByAppname(String appname) {
        logger.info("appname : {}", appname);
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(AppConfig.class);
        crit.add(Restrictions.eq("appname", appname));
        AppConfig appconfig = (AppConfig)crit.uniqueResult();

        return appconfig;
    }
 
}
