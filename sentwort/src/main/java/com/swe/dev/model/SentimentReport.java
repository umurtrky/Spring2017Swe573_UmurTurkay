package com.swe.dev.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

public class SentimentReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
    
    private String message;
    
    private String account;
    
    private Integer sentiment;
    
    private String links;
    
    private Date sharedate;
    
    private String hashtag;
    
    public SentimentReport(){
    	
    }
    
    public SentimentReport(String id, String message, String account, Integer sentiment, String links, Date sharedate, String hashtag){
    	super();
    	this.id = id;
    	this.message = message;
    	this.account = account;
    	this.sentiment = sentiment;
    	this.links = links;
    	this.sharedate = sharedate;
    	this.hashtag = hashtag;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getSentiment() {
		return sentiment;
	}

	public void setSentiment(Integer sentiment) {
		this.sentiment = sentiment;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public Date getSharedate() {
		return sharedate;
	}

	public void setSharedate(Date sharedate) {
		this.sharedate = sharedate;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
}
