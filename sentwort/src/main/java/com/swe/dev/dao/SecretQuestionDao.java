package com.swe.dev.dao;

import java.util.List;

import com.swe.dev.model.SecretQuestion;
 
 
public interface SecretQuestionDao {
 
	SecretQuestion findById(int id);
	List<SecretQuestion> findAllQuestions();
 
}