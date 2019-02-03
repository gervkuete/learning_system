package com.gervkuete.dao;

import java.util.List;

import com.gervkuete.model.Exam;

public interface ExamDAO {
	
	// declare methods for performing operations on Exam
	public Exam findExamById(int id);
	public Exam findExamByTitle(String title);
	public int getExamId(String title);
	public List<Exam> getAllExams();
	public boolean addExam(Exam exam);
	public boolean updateExam(Exam exam);
	public boolean deleteExam(Exam exam);
	

}
