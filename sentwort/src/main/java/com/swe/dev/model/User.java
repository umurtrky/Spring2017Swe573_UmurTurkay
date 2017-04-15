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
 
import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name="USER")
public class User implements Serializable{
 
    
    private Integer id;
 
    
    private String username;
     
    
    private String password;
         
    
    private String secretquestion;
 
    
    private String secretanswer;
 
    
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
    @Column(name="password", nullable=false)
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    @NotEmpty
    @Column(name="username", unique=true, nullable=false)
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty
    @Column(name="secretquestion", nullable=false)
	public String getSecretquestion() {
		return secretquestion;
	}

	public void setSecretquestion(String secretquestion) {
		this.secretquestion = secretquestion;
	}

	@NotEmpty
    @Column(name="secretanswer", nullable=false)
	public String getSecretanswer() {
		return secretanswer;
	}

	public void setSecretanswer(String secretanswer) {
		this.secretanswer = secretanswer;
	}

	@NotEmpty
    @Column(name="createdate", nullable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@NotEmpty
    @Column(name="updatedate", nullable=false)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + "]";
    }
 
 
     
}