package com.swe.dev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USERHASHTAG")
public class UserHashtag implements Serializable {

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userid", column = @Column(name = "userid", nullable = false)),
			@AttributeOverride(name = "hashtagid", column = @Column(name = "hashtagid", nullable = false)) })
	UserHashtagId id;	
 
    
    private Date createdate;


	public UserHashtagId getId() {
		return id;
	}


	public void setId(UserHashtagId id) {
		this.id = id;
	}


	@NotNull
    @Column(name="createdate", nullable=false)
	public Date getCreatedate() {
		return createdate;
	}


	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
}
