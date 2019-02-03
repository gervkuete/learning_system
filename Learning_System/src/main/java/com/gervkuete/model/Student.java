package com.gervkuete.model;

import java.sql.Date;

public class Student extends User {

	private String lastName;
	private String firstName;
	private String sex;
	private String country;
	private Date dateOfBirth;

	public Student() {
	}

	public Student(int id, String lastName, String firstName, String email, String password, String sex, String country,
			Date dateOfBirth) {
		super(id, email, password);
		this.lastName = lastName;
		this.firstName = firstName;
		this.sex = sex;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
	}

	// getters and setters methods

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex.trim().substring(0, 1);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
