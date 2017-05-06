package com.swe.dev.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swe.dev.model.Hashtag;
import com.swe.dev.model.HashtagReport;

@Service("hashtagService")
@Transactional
public class HashtagServiceImpl implements HashtagService {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
    UserService userService;
	
	public List<Hashtag> findByUser(String username){
		List<Hashtag> hashtags = new ArrayList<Hashtag>();
		
		Query<Hashtag> query = sessionFactory.getCurrentSession().createQuery("SELECT ht from Hashtag ht, UserHashtag uht "
				+ "where uht.id.hashtagid=ht.id and uht.id.userid = :userid");
		query.setParameter("userid", userService.findByUsername(username).getId());
		
		hashtags.addAll(query.list());
		return hashtags;
	}
	
	public List<Hashtag> findByMessageId(String messageid){
		List<Hashtag> hashtags = new ArrayList<Hashtag>();
		
		Query<Hashtag> query = sessionFactory.getCurrentSession().createQuery("SELECT ht from Hashtag ht, MessageHashtag mht "
				+ "where mht.id.hashtagid=ht.id and mht.id.messageid = :messageid");
		query.setParameter("messageid", messageid);
		
		hashtags.addAll(query.list());
		return hashtags;
	}
	
	public List<HashtagReport> getReport(){
		List<Object> reports = new ArrayList<Object>();
		/*select hashtagname, count(message.id), sentiment from hashtag join messagehashtag
on hashtag.id = messagehashtag.hashtagid join message on message.id = messagehashtag.messageid
group by sentiment, hashtagname;*/
		Query<HashtagReport> query = sessionFactory.getCurrentSession().createQuery("SELECT ht.hashtagname, count(m.id), m.sentiment from Hashtag ht, MessageHashtag mht, Message m "
				+ "where mht.id.hashtagid=ht.id and mht.id.messageid = m.id "
				+ "group by m.sentiment, ht.hashtagname");
		
		reports.addAll(query.list());
		List<HashtagReport> result = new ArrayList<HashtagReport>();
		for(int i=0;i<reports.size();i++){
			HashtagReport report = new HashtagReport();
			report.setHashtagname(((Object[])reports.get(i))[0].toString());
			report.setId(i);
			report.setNumOfTweets(((Long)((Object[])reports.get(i))[1]).intValue());
			report.setSentiment(((Integer)((Object[])reports.get(i))[2]));
			result.add(report);
		}
		return result;
	}
	
	public Long getNumberOfHashtags(int isactive){
		Long number = Long.valueOf("0");
		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("SELECT count(ht.id) from Hashtag ht");
		if(isactive != -1){
			query = sessionFactory.getCurrentSession().createQuery("SELECT count(ht.id) from Hashtag ht where ht.isactive = :isactive");
			query.setParameter("isactive", isactive);
		}
		number = (Long)query.uniqueResult();
		return number;
	}

}
