package com.eshop.model;

import java.util.Date;
import java.util.List;


/**
 * Order Class - Defines the attributes of an order.
 */
public class Order {
	
	/** The order id. */
	String orderId;
	
	/** The order date. */
	Date orderDate;
	
	/** The user. */
	User user;
	
	/** The item list. */
	List <OrderItem>itemList;
	
	/** The total amount. */
	double totalAmount;
	
	/** The discount amount. */
	double discountAmount;
	
	/** The final amount. */
	double finalAmount;
	
	/**
	 * Instantiates a new order.
	 */
	public Order()
	{
		this.orderId = "";
		this.user = null;
		this.itemList = null;
		this.totalAmount = 0.0;
		this.discountAmount = 0.0;
		this.finalAmount = 0.0;
	}
	
	
	
	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * Gets the order date.
	 *
	 * @return the order date
	 */
	public Date getOrderDate() {
		return orderDate;
	}


	/**
	 * Sets the order date.
	 *
	 * @param orderDate the new order date
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Gets the item list.
	 *
	 * @return the item list
	 */
	public List<OrderItem> getItemList() {
		return itemList;
	}
	
	/**
	 * Sets the item list.
	 *
	 * @param itemList the new item list
	 */
	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}
	
	/**
	 * Gets the total amount.
	 *
	 * @return the total amount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * Sets the total amount.
	 *
	 * @param totalAmount the new total amount
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * Gets the discount amount.
	 *
	 * @return the discount amount
	 */
	public double getDiscountAmount() {
		return discountAmount;
	}
	
	/**
	 * Sets the discount amount.
	 *
	 * @param discountAmount the new discount amount
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	/**
	 * Gets the final amount.
	 *
	 * @return the final amount
	 */
	public double getFinalAmount() {
		return finalAmount;
	}
	
	/**
	 * Sets the final amount.
	 *
	 * @param finalAmount the new final amount
	 */
	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
	
	

}
