package org.CMPE202.starbucks.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -7788619177798333712L;
	private String userID;
	private String userPin;
	private String userName;
	private String createdDate;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPin() {
		return userPin;
	}

	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


//	private String firstname;
//	private String lastname;
//	private String createdDate;
//	private String modifiedDate;
//	private String password;
//	private String confirmPassword;


	

}
