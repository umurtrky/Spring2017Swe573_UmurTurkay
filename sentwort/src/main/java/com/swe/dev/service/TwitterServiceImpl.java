package com.swe.dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swe.dev.converter.StatusMessageConverter;
import com.swe.dev.dao.AppConfigDao;
import com.swe.dev.model.AppConfig;
import com.swe.dev.model.Message;

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
	
	@Autowired
	AppConfigDao appConfigDao;

	@Override
	public List<Message> getTweets() {
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
			
			Query query = new Query("#DoctorWho");
			QueryResult searchResult = twitter.search(query);
			List<Status> tweets = searchResult.getTweets();
			List<Message> messages = new ArrayList<Message>();
			for (Status tweet : tweets) {
                System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                messages.add(StatusMessageConverter.convert(tweet));
            }
		
			return messages;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
