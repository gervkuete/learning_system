package com.gervkuete.dao;

import java.util.List;

import com.gervkuete.model.Student;

public interface StudentDAO {
	
	String addStudent (Student std);
	Student findStudentById(int id);
	Student findStudentByUserId(int userId);
	List<Student> getAllStudents();

}
