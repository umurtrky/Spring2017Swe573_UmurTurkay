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
@Table(name="MESSAGEHASHTAG")
public class MessageHashtag implements Serializable {

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "messageid", column = @Column(name = "messageid", nullable = false)),
			@AttributeOverride(name = "hashtagid", column = @Column(name = "hashtagid", nullable = false)) })
	MessageHashtagId id;	
 
    
    private Date createdate;


	public MessageHashtagId getId() {
		return id;
	}


	public void setId(MessageHashtagId id) {
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
