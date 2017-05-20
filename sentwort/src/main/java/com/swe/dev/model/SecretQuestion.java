package com.swe.dev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SECRETQUESTION")
public class SecretQuestion {
	
	private Integer id;
 
    
    private String secretquestion;


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
    @Column(name="secretquestion", nullable=false)
	public String getSecretquestion() {
		return secretquestion;
	}


	public void setSecretquestion(String secretquestion) {
		this.secretquestion = secretquestion;
	}

}
