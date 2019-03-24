package com.eshop.model;


/**
 * OrderItem Class - Defines the attributes of an order item.
 */
public class OrderItem {
	
	/** The product. */
	Product product;
	
	/** The quantity. */
	int quantity;
	
	/** The item cost. */
	double itemCost;
	
	/**
	 * Instantiates a new order item.
	 */
	public OrderItem()
	{
		this.product = null;
		this.quantity = 0;
		this.itemCost = 0.0;
	}
	
	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Gets the item cost.
	 *
	 * @return the item cost
	 */
	public double getItemCost() {
		return itemCost;
	}
	
	/**
	 * Sets the item cost.
	 *
	 * @param itemCost the new item cost
	 */
	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}
	
	
	

}
