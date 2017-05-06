package com.swe.dev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;


public class HashtagReport implements Serializable {
	private Integer id; 
 
    private String hashtagname;
     
    private Integer numOfTweets;
    
    private Integer sentiment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHashtagname() {
		return hashtagname;
	}

	public void setHashtagname(String hashtagname) {
		this.hashtagname = hashtagname;
	}

	public Integer getNumOfTweets() {
		return numOfTweets;
	}

	public void setNumOfTweets(Integer numOfTweets) {
		this.numOfTweets = numOfTweets;
	}

	public Integer getSentiment() {
		return sentiment;
	}

	public void setSentiment(Integer sentiment) {
		this.sentiment = sentiment;
	}
}
