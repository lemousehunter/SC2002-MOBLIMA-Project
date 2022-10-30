package com.moblima.app;

public class User {

	private UserType userType;
	private String userName;

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User(UserType userType, String userName) {
		this.userType = userType;
		this.userName = userName;
	}

}