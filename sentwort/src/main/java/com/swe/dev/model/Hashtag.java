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
@Table(name="HASHTAG")
public class Hashtag implements Serializable{
 
    
    private Integer id;
 
    
    private String hashtagname;
     
    
    private Integer isactive;
         
    
    private Date createdate;
 
    
    private Date startdate;
    
    
    private Date stopdate;
 
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
    @Column(name="hashtagname", nullable=false)
	public String getHashtagname() {
		return hashtagname;
	}

	public void setHashtagname(String hashtagname) {
		this.hashtagname = hashtagname;
	}

	@NotNull
    @Column(name="isactive", nullable=false)
	public Integer getIsactive() {
		return isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
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
    @Column(name="startdate", nullable=false)
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

    @Column(name="stopdate")
	public Date getStopdate() {
		return stopdate;
	}

	public void setStopdate(Date stopdate) {
		this.stopdate = stopdate;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((hashtagname == null) ? 0 : hashtagname.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Hashtag))
            return false;
        Hashtag other = (Hashtag) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (hashtagname == null) {
            if (other.hashtagname != null)
                return false;
        } else if (!hashtagname.equals(other.hashtagname))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Hashtag [id=" + id + ", name=" + hashtagname + "]";
    }
 
 
     
}