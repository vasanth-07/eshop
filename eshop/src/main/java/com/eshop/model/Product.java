package com.eshop.model;


/**
 * Product Class - Defines the attributes of a product.
 */
public class Product {
	
	/** The product id. */
	String productId;
	
	/** The name. */
	String name;
	
	/** The description. */
	String description;
	
	/** The unit price. */
	double unitPrice;
	
	/** The product type. */
	PRODUCT_TYPE productType;
	
	/**
	 * The Enum PRODUCT_TYPE.
	 */
	public enum PRODUCT_TYPE {
		
		/** Product belongs to type GROCERY */
		GROCERY, 
		/** Product belongs to type ELECTRONICS */
		ELECTRONICS, 
		/** Product belongs to type MEDICAL */
		MEDICAL
	}
	
	/**
	 * Instantiates a new product.
	 */
	public Product()
	{
		this.productId = "";
		this.name = "";
		this.description = "";
		this.unitPrice = 0.0;
		
	}
	
	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId() {
		return productId;
	}
	
	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the unit price.
	 *
	 * @return the unit price
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	
	/**
	 * Sets the unit price.
	 *
	 * @param unitPrice the new unit price
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * Gets the product type.
	 *
	 * @return the product type
	 */
	public PRODUCT_TYPE getProductType() {
		return productType;
	}
	
	/**
	 * Sets the product type.
	 *
	 * @param productType the new product type
	 */
	public void setProductType(PRODUCT_TYPE productType) {
		this.productType = productType;
	}
	
	
	

}
