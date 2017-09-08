package com.retailstore.pojo;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private Float cost;
	private Category category;
	private String productName;
	
	public Product(){
		
	}

	public Product(Integer productId, Float cost, Category category, String productName) {
		this.productId = productId;
		this.cost = cost;
		this.category = category;
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

}
