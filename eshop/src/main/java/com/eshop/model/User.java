package com.eshop.model;

import java.util.Date;


/**
 * User Class - Defines the attributes of a user.
 */
public class User {
	
	/**
	 * The Enum USER_TYPE.
	 */
	public enum USER_TYPE {
		
			/** User is an employee. */
			 EMPLOYEE, 
		   /** User is an affiliate. */
			AFFILIATE, 
		 /** User is not an employee or affiliate */
			NONE
	}
	
	
	/** User Id */
	String custId;	
	
	/** The first name. */
	String firstName;
	
	/** The last name. */
	String lastName;
	
	/** The address. */
	String address;
	
	/** The user type. */
	USER_TYPE userType;
	
	/** The registration date. */
	Date registrationDate;
	
	/** The last transacted date. */
	Date lastTransactedDate;
	
	/**
	 * Instantiates a new user.
	 */
	public User()
	{
		this.setCustId("");
		this.setFirstName("");
		this.setLastName("");
		this.setUserType(USER_TYPE.NONE);
	}
	
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param custId the new user id
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public USER_TYPE getUserType() {
		return userType;
	}
	
	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(USER_TYPE userType) {
		this.userType = userType;
	}
	
	/**
	 * Gets the registration date.
	 *
	 * @return the registration date
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	/**
	 * Sets the registration date.
	 *
	 * @param registrationDate the new registration date
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	/**
	 * Gets the last transacted date.
	 *
	 * @return the last transacted date
	 */
	public Date getLastTransactedDate() {
		return lastTransactedDate;
	}
	
	/**
	 * Sets the last transacted date.
	 *
	 * @param lastTransactedDate the new last transacted date
	 */
	public void setLastTransactedDate(Date lastTransactedDate) {
		this.lastTransactedDate = lastTransactedDate;
	}
	
	

}
