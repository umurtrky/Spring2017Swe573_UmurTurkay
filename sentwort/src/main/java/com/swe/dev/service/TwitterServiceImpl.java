package com.swe.dev.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swe.dev.converter.StatusMessageConverter;
import com.swe.dev.dao.AppConfigDao;
import com.swe.dev.dao.HashtagDao;
import com.swe.dev.dao.MessageHashtagDao;
import com.swe.dev.dao.UserDaoImpl;
import com.swe.dev.model.AppConfig;
import com.swe.dev.model.Hashtag;
import com.swe.dev.model.Message;
import com.swe.dev.model.MessageHashtag;
import com.swe.dev.model.MessageHashtagId;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service("twitterService")
@Component
public class TwitterServiceImpl implements TwitterService {
	
	static final Logger logger = LoggerFactory.getLogger(TwitterServiceImpl.class);
	
	@Autowired
	AppConfigDao appConfigDao;
	
	@Autowired
	HashtagDao hashtagDao;
	
	@Autowired 
    private MessageHashtagDao messagehashtagDao;
	
	@Autowired
	MessageService messageService;

	@Override
	public List<Message> getTweets(List<Hashtag> hashtags) {
		List<Message> messages = new ArrayList<Message>();
		try {
			AppConfig appconfig = appConfigDao.findByAppname("sentwort");
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey(appconfig.getConsumerkey())
			  .setOAuthConsumerSecret(appconfig.getConsumersecret())
			  .setOAuthAccessToken(appconfig.getAccesstoken())
			  .setOAuthAccessTokenSecret(appconfig.getAccesstokensecret());
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			
			String queryString = "";
			for(int i=0; i<hashtags.size(); i++){
				if(i!=hashtags.size()-1)
					queryString += "#" + hashtags.get(i).getHashtagname() + " OR ";
				else
					queryString += "#" + hashtags.get(i).getHashtagname();
			}
			Query query = new Query(queryString);
			query.setCount(200);
			if(appconfig.getSinceid() != "0"){
				query.setSinceId(Long.parseLong(appconfig.getSinceid()));
			}
//			if(appconfig.getMaxid() != 0){
//				query.setSinceId(appconfig.getMaxid());
//			}
			QueryResult searchResult = twitter.search(query);
			List<Status> tweets = searchResult.getTweets();
			if(tweets.size() > 0){
				String sinceid = String.valueOf(tweets.get(0).getId());
				//Long.parseLong(String.valueOf(tweets.get(tweets.size()-1).getId()));
				//System.out.println("sinceid: " + sinceid);
				appConfigDao.updateSinceId(appconfig, sinceid);
				
				for (Status tweet : tweets) {
	                //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + " - " + tweet.getId());
	                messages.add(StatusMessageConverter.convert(tweet));
	                messageService.save(StatusMessageConverter.convert(tweet));
	                
	                HashtagEntity[] hashtagsInTweet = tweet.getHashtagEntities();
	                for(HashtagEntity entity : hashtagsInTweet){
	                	Hashtag hashtag = hashtagDao.findByName(entity.getText());
	                	if(hashtag != null){
	                		MessageHashtag messageHashtag = new MessageHashtag();
	                		messageHashtag.setId(new MessageHashtagId(String.valueOf(tweet.getId()), hashtag.getId()));
	                		messageHashtag.setCreatedate(new Date());
	                		messagehashtagDao.save(messageHashtag);
	                	}
	                }
	            }
			}
			else{
				logger.info("Twitter Search API returned zero tweets.");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error while retrieving tweets: " + e.getMessage());
			e.printStackTrace();
		}
		return messages;
	}
	
	public List<Message> retrieveTweets() {
		try {
			AppConfig appconfig = appConfigDao.findByAppname("sentwort");
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey(appconfig.getConsumerkey())
			  .setOAuthConsumerSecret(appconfig.getConsumersecret())
			  .setOAuthAccessToken(appconfig.getAccesstoken())
			  .setOAuthAccessTokenSecret(appconfig.getAccesstokensecret());
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			
			String hashtag = "";
			List<Hashtag> actives = new ArrayList<Hashtag>();
			actives = hashtagDao.findActiveHashtags();
			List<Message> messages = new ArrayList<Message>();
			if(actives.size()>0){
				hashtag = actives.get(actives.size()-1).getHashtagname();
				Query query = new Query("#" + hashtag);
				query.setCount(50);
				QueryResult searchResult = twitter.search(query);
				List<Status> tweets = searchResult.getTweets();
				
				for (Status tweet : tweets) {
	                messages.add(StatusMessageConverter.convert(tweet));
	            }
			}
			
			return messages;
		
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
