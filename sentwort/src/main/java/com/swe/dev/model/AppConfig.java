package com.swe.dev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APPCONFIG")
public class AppConfig implements Serializable{

	private Integer id;
 
    
    private String appname;
     
    
    private String consumerkey;
         
    
    private String consumersecret;
 
    
    private String accesstoken;
    
    private String accesstokensecret;
    
    private Date createDate;
    
    
    private Date updateDate;


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@NotEmpty
    @Column(name="appname", nullable=false)
	public String getAppname() {
		return appname;
	}


	public void setAppname(String appname) {
		this.appname = appname;
	}


	@NotEmpty
    @Column(name="consumerkey", nullable=false)
	public String getConsumerkey() {
		return consumerkey;
	}


	public void setConsumerkey(String consumerkey) {
		this.consumerkey = consumerkey;
	}


	@NotEmpty
    @Column(name="consumersecret", nullable=false)
	public String getConsumersecret() {
		return consumersecret;
	}


	public void setConsumersecret(String consumersecret) {
		this.consumersecret = consumersecret;
	}


	@NotEmpty
    @Column(name="accesstoken", nullable=false)
	public String getAccesstoken() {
		return accesstoken;
	}


	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}


	@NotEmpty
    @Column(name="accesstokensecret", nullable=false)
	public String getAccesstokensecret() {
		return accesstokensecret;
	}


	public void setAccesstokensecret(String accesstokensecret) {
		this.accesstokensecret = accesstokensecret;
	}


	@NotEmpty
    @Column(name="createDate", nullable=false)
	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@NotEmpty
    @Column(name="updateDate")
	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
