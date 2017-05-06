package com.swe.dev.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name="MESSAGE")
public class Message implements Serializable{
    
    private String id;
 
    
    private String message;
     
    
    private String account;
         
    
    private Integer sentiment;
 
    
    private String links;
 
    
    private Date createdate;
    
    
    private Date sharedate;
 
//    @NotEmpty
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "APP_USER_USER_PROFILE", 
//             joinColumns = { @JoinColumn(name = "USER_ID") }, 
//             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
//    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
 
    @Id
    @Column(name="id", nullable=false)
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    @NotEmpty
    @Column(name="message", nullable=false)
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@NotEmpty
    @Column(name="account", nullable=false)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@NotNull
    @Column(name="sentiment", nullable=false)
	public Integer getSentiment() {
		return sentiment;
	}

	public void setSentiment(Integer sentiment) {
		this.sentiment = sentiment;
	}

	@NotEmpty
    @Column(name="links")
	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	@NotNull
	@Column(name="createdate", nullable=false)
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@NotNull
    @Column(name="sharedate", nullable=false)
	public Date getSharedate() {
		return sharedate;
	}

	public void setSharedate(Date sharedate) {
		this.sharedate = sharedate;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        Message other = (Message) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Message [id=" + id + ", account=" + account + ", message=" + message + "]";
    }
 
 
     
}