package com.swe.dev.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swe.dev.dao.HashtagDao;
import com.swe.dev.model.Hashtag;
import com.swe.dev.model.HashtagReport;
import com.swe.dev.model.SentimentReport;

@Service("hashtagService")
@Transactional
public class HashtagServiceImpl implements HashtagService {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
    UserService userService;
	
	@Autowired
	HashtagDao dao;
	
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
	
//	public List<HashtagReport> getReport(){
//		List<Object> reports = new ArrayList<Object>();
//		/*select hashtagname, count(message.id), sentiment from hashtag join messagehashtag
//on hashtag.id = messagehashtag.hashtagid join message on message.id = messagehashtag.messageid
//group by sentiment, hashtagname;*/
//		Query<HashtagReport> query = sessionFactory.getCurrentSession().createQuery("SELECT ht.hashtagname, count(m.id), m.sentiment from Hashtag ht, MessageHashtag mht, Message m "
//				+ "where mht.id.hashtagid=ht.id and mht.id.messageid = m.id "
//				+ "group by m.sentiment, ht.hashtagname");
//		
//		reports.addAll(query.list());
//		List<HashtagReport> result = new ArrayList<HashtagReport>();
//		for(int i=0;i<reports.size();i++){
//			HashtagReport report = new HashtagReport();
//			report.setHashtagname(((Object[])reports.get(i))[0].toString());
//			report.setId(i);
//			report.setNumOfTweets(((Long)((Object[])reports.get(i))[1]).intValue());
//			report.setSentiment(((Integer)((Object[])reports.get(i))[2]));
//			result.add(report);
//		}
//		return result;
//	}
	
	public List<HashtagReport> getReport(){
		List<HashtagReport> result = new ArrayList<HashtagReport>();
		
		Query<HashtagReport> query = sessionFactory.getCurrentSession().createNativeQuery("select hashtagname, sum(numOfTweets) as numOfTweets, sum(numOfNonanalyzedTweets) as numOfNonanalyzedTweets, sum(numOfNegative) as numOfNegative, sum(numOfNeutral) as numOfNeutral, sum(numOfPositive) as numOfPositive from "                 
			+"(select hashtagname, count(m.id) as numOfTweets, 0 as numOfNonanalyzedTweets, 0 as numOfNegative, 0 as numOfNeutral, 0 as numOfPositive from hashtag ht join messagehashtag mt on ht.id = mt.hashtagid "
			+"join message m on m.id = mt.messageid "
			 +"group by hashtagname "
			 +"union all "
			+"select hashtagname, 0 as numOfTweets, count(m.id) as numOfNonanalyzedTweets, 0 as numOfNegative, 0 as numOfNeutral, 0 as numOfPositive from hashtag ht join messagehashtag mt on ht.id = mt.hashtagid "
			+"join message m on m.id = mt.messageid where m.sentiment = -1 "
			 +"group by hashtagname "
			 +"union all "
			 +"select hashtagname, 0 as numOfTweets, 0 as numOfNonanalyzedTweets, count(m.id) as numOfNegative, 0 as numOfNeutral, 0 as numOfPositive from hashtag ht join messagehashtag mt on ht.id = mt.hashtagid "
			+"join message m on m.id = mt.messageid where m.sentiment = 0 "
			 +"group by hashtagname "
			 +"union all "
			 +"select hashtagname, 0 as numOfTweets, 0 as numOfNonanalyzedTweets, 0 as numOfNegative, count(m.id) as numOfNeutral, 0 as numOfPositive from hashtag ht join messagehashtag mt on ht.id = mt.hashtagid "
			+"join message m on m.id = mt.messageid where m.sentiment = 2 "
			 +"group by hashtagname "
			 +"union all "
			 +"select hashtagname, 0 as numOfTweets, 0 as numOfNonanalyzedTweets, 0 as numOfNegative, 0 as numOfNeutral, count(m.id) as numOfPositive from hashtag ht join messagehashtag mt on ht.id = mt.hashtagid "
			+"join message m on m.id = mt.messageid where m.sentiment = 4 "
			 +"group by hashtagname) as result group by hashtagname; ");
		
//		Query<HashtagReport> query = sessionFactory.getCurrentSession().createQuery("select hashtagname, sum(numOfTweets) as numOfTweets, sum(numOfNonanalyzedTweets) as numOfNonanalyzedTweets, sum(numOfNegative) as numOfNegative, sum(numOfNeutral) as numOfNeutral, sum(numOfPositive) as numOfPositive from "                
//				+"(select hashtagname, count(m.id) as numOfTweets, 0 as numOfNonanalyzedTweets, 0 as numOfNegative, 0 as numOfNeutral, 0 as numOfPositive from Hashtag ht, MessageHashtag mt, Message m where ht.id = mt.id.hashtagid "
//				+"and m.id = mt.id.messageid "
//				+"group by hashtagname "
//				 +"union all "
//				+"select hashtagname, 0 as numOfTweets, count(m.id) as numOfNonanalyzedTweets, 0 as numOfNegative, 0 as numOfNeutral, 0 as numOfPositive from Hashtag ht, MessageHashtag mt, Message m where ht.id = mt.id.hashtagid "
//				+"and m.id = mt.id.messageid and m.sentiment = -1 "
//				+"group by hashtagname "
//				+"union all "
//				 +"select hashtagname, 0 as numOfTweets, 0 as numOfNonanalyzedTweets, count(m.id) as numOfNegative, 0 as numOfNeutral, 0 as numOfPositive from Hashtag ht, MessageHashtag mt, Message m where ht.id = mt.id.hashtagid "
//				+"and m.id = mt.id.messageid and m.sentiment = 0 "
//				+"group by hashtagname "
//				 +"union all "
//				 +"select hashtagname, 0 as numOfTweets, 0 as numOfNonanalyzedTweets, 0 as numOfNegative, count(m.id) as numOfNeutral, 0 as numOfPositive from Hashtag ht, MessageHashtag mt, Message m where ht.id = mt.id.hashtagid "
//				+"and m.id = mt.id.messageid and m.sentiment = 2 "
//						+"group by hashtagname "
//				+"union all "
//				+"select hashtagname, 0 as numOfTweets, 0 as numOfNonanalyzedTweets, 0 as numOfNegative, 0 as numOfNeutral, count(m.id) as numOfPositive from Hashtag ht, MessageHashtag mt, Message m where ht.id = mt.id.hashtagid "
//				+"and m.id = mt.id.messageid and m.sentiment = 4 "
//				+"group by hashtagname) as result group by hashtagname",HashtagReport.class);
		
		List<Object> reports = new ArrayList<Object>();
		reports.addAll(query.list());
		
		for(int i=0;i<reports.size();i++){
			//hashtagname, sum(numOfTweets) as numOfTweets, sum(numOfNonanalyzedTweets) as numOfNonanalyzedTweets, 
			//sum(numOfNegative) as numOfNegative, sum(numOfNeutral) as numOfNeutral, sum(numOfPositive) as numOfPositive
			HashtagReport report = new HashtagReport();
			report.setHashtagname(((Object[])reports.get(i))[0].toString());
			report.setNumOfTweets(((BigDecimal)((Object[])reports.get(i))[1]).intValue());
			report.setNumOfNonanalyzedTweets(((BigDecimal)((Object[])reports.get(i))[2]).intValue());
			report.setNumOfNegativeTweets(((BigDecimal)((Object[])reports.get(i))[3]).intValue());
			report.setNumOfNeutralTweets(((BigDecimal)((Object[])reports.get(i))[4]).intValue());
			report.setNumOfPositiveTweets(((BigDecimal)((Object[])reports.get(i))[5]).intValue());
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
	
	public List<Hashtag> findAllHashtags(){
		return dao.findAllHashtags();
	}

}
