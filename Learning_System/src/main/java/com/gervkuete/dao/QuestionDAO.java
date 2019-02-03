package com.gervkuete.dao;

import java.util.List;

import com.gervkuete.model.Question;

public interface QuestionDAO {

	
	// declare methods for performing operations on Question
	public Question findQuestionById(int id);
	public List<Question> getAllQuestions();
	public boolean addQuestion(Question question);
	public boolean deleteQuestion(Question question);
	public boolean updateQuestion(Question question);
	
	
	
}
