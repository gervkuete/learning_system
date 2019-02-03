package com.gervkuete.model;

public class Question {

	private int id;
	private String text;
	private int answer;
	private int examID;

	// constructors

	public Question() {

	}

	public Question(String text, int answer, int examID) {
		this.text = text;
		this.answer = answer;
		this.examID = examID;
	}

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getExamID() {
		return examID;
	}

	public void setExamID(int examID) {
		this.examID = examID;
	}
	
	public boolean isCorrectAnswer(int answer) {
		return this.getAnswer() == answer;
	}
}
