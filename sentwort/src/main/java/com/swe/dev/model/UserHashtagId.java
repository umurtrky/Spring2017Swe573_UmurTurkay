package com.swe.dev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserHashtagId implements Serializable {
	
	@Column(name = "userid")
	Integer userid;
	
	@Column(name = "hashtagid")
	Integer hashtagid;

	public UserHashtagId() {
		
	}
	
	public UserHashtagId(Integer userid, Integer hashtagid) {
		this.userid = userid;
		this.hashtagid = hashtagid;		
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getHashtagid() {
		return hashtagid;
	}

	public void setHashtagid(Integer hashtagid) {
		this.hashtagid = hashtagid;
	}

}
