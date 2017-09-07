package com.retailstore.pojo;

import java.io.Serializable;

public class TransactionItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int productId;
	int productQuantity;
	float costWithoutTax;
	float serviceTax;

	public TransactionItem() {

	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public float getCostWithoutTax() {
		return costWithoutTax;
	}

	public void setCostWithoutTax(float costWithoutTax) {
		this.costWithoutTax = costWithoutTax;
	}

	public float getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(float serviceTax) {
		this.serviceTax = serviceTax;
	}

}
