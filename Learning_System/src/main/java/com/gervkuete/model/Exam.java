package com.gervkuete.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Exam {

	private int id;
	private String title;
	private Date date;
	private List<Question> questions;

	public Exam() {

		questions = new LinkedList<>();
	}

	public Exam(String title, Date date, List<Question> questions) {
		this.title = title;
		this.date = date;
		this.questions = questions;
	}

	// set getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
