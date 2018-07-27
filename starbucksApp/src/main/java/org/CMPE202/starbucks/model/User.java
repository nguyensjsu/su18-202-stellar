package org.CMPE202.starbucks.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -7788619177798333712L;
	private String userID;
	private String userName;
	//private String lastname;
	private String createdDate;
	//private String modifiedDate;
	private String userPin;
	//private String confirmPassword;

	/*public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}*/


	public String getPin() {
		return userPin;
	}

	public void setPin(String userPin) {
		this.userPin = userPin;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String firstname) {
		this.userName = userName;
	}
	/*public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}*/
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/*public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}*/
	

}
