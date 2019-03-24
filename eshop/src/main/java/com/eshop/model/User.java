package com.eshop.model;

import java.util.Date;

public class User {
	
	public enum USER_TYPE {
		EMPLOYEE, AFFILIATE, NONE
	}
	
	
	String custId;	
	String firstName;
	String lastName;
	String address;
	USER_TYPE userType;
	Date registrationDate;
	Date lastTransactedDate;
	
	/**
	 * 
	 */
	public User()
	{
		this.setCustId("");
		this.setFirstName("");
		this.setLastName("");
		this.setUserType(USER_TYPE.NONE);
	}
	
	
	/**
	 * @return
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return
	 */
	public USER_TYPE getUserType() {
		return userType;
	}
	
	/**
	 * @param userType
	 */
	public void setUserType(USER_TYPE userType) {
		this.userType = userType;
	}
	
	/**
	 * @return
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	/**
	 * @param registrationDate
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	/**
	 * @return
	 */
	public Date getLastTransactedDate() {
		return lastTransactedDate;
	}
	
	/**
	 * @param lastTransactedDate
	 */
	public void setLastTransactedDate(Date lastTransactedDate) {
		this.lastTransactedDate = lastTransactedDate;
	}
	
	

}
