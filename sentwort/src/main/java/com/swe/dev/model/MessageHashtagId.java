package com.swe.dev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MessageHashtagId implements Serializable {
	
	@Column(name = "messageid")
	String messageid;
	
	@Column(name = "hashtagid")
	Integer hashtagid;

	public MessageHashtagId() {
		
	}
	
	public MessageHashtagId(String messageid, Integer hashtagid) {
		this.messageid = messageid;
		this.hashtagid = hashtagid;		
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public Integer getHashtagid() {
		return hashtagid;
	}

	public void setHashtagid(Integer hashtagid) {
		this.hashtagid = hashtagid;
	}

}
