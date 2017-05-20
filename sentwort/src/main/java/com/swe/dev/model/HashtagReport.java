package com.swe.dev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;


public class HashtagReport implements Serializable {
 
    private String hashtagname;
     
    private Integer numOfTweets;
    
    private Integer numOfNonanalyzedTweets;
    
    private Integer numOfPositiveTweets;
    
    private Integer numOfNeutralTweets;
    
    private Integer numOfNegativeTweets;
    
    public HashtagReport(){
    	
    }
    
    public HashtagReport(String hashtagname, Integer numOfTweets, Integer numOfNonanalyzedTweets, Integer numOfPositiveTweets, Integer numOfNeutralTweets, Integer numOfNegativeTweets){
    	this.hashtagname = hashtagname;
    	this.numOfTweets = numOfTweets;
    	this.numOfNonanalyzedTweets = numOfNonanalyzedTweets;
    	this.numOfPositiveTweets = numOfPositiveTweets;
    	this.numOfNeutralTweets = numOfNeutralTweets;
    	this.numOfNegativeTweets = numOfNegativeTweets;
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

	public Integer getNumOfNonanalyzedTweets() {
		return numOfNonanalyzedTweets;
	}

	public void setNumOfNonanalyzedTweets(Integer numOfNonanalyzedTweets) {
		this.numOfNonanalyzedTweets = numOfNonanalyzedTweets;
	}

	public Integer getNumOfPositiveTweets() {
		return numOfPositiveTweets;
	}

	public void setNumOfPositiveTweets(Integer numOfPositiveTweets) {
		this.numOfPositiveTweets = numOfPositiveTweets;
	}

	public Integer getNumOfNeutralTweets() {
		return numOfNeutralTweets;
	}

	public void setNumOfNeutralTweets(Integer numOfNeutralTweets) {
		this.numOfNeutralTweets = numOfNeutralTweets;
	}

	public Integer getNumOfNegativeTweets() {
		return numOfNegativeTweets;
	}

	public void setNumOfNegativeTweets(Integer numOfNegativeTweets) {
		this.numOfNegativeTweets = numOfNegativeTweets;
	}
}
