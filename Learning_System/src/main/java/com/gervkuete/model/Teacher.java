package com.gervkuete.model;

public class Teacher {

	private String surname;
	private String name;
	private String email;
	private String phoneNumber;
	
	public Teacher(String surname, String name, String email, String phoneNumber) {
		this.surname = surname;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
