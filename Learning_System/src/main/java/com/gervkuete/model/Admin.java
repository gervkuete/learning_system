package com.gervkuete.model;

public class Admin extends User {

	private String adminName;

	public Admin() {

	}
	
	public Admin(String adminName) {
		
		this.adminName = adminName;
	}

	// Getters and setters
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
