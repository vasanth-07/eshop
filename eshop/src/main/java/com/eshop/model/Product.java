package com.eshop.model;

public class Product {
	
	String productId;
	String name;
	String description;
	double unitPrice;
	
	PRODUCT_TYPE productType;
	
	public enum PRODUCT_TYPE {
		GROCERY, ELECTRONICS, MEDICAL
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public PRODUCT_TYPE getProductType() {
		return productType;
	}
	public void setProductType(PRODUCT_TYPE productType) {
		this.productType = productType;
	}
	
	
	

}
