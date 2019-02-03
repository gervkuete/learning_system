package com.gervkuete.model;

public class User {

	private int id;
	private String email;
	private String password;
	private int role;

	// constructors
	public User() {

	}

	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	// Getters and setters methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	// verify if the user can log in
	public boolean enter(String email, String password) {
		return false;
	}
}
