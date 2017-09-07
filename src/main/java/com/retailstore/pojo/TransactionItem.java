package com.retailstore.pojo;

public class TransactionItem {

	Integer productId;
	Integer productQuantity;
	Float costWithoutTax;
	Float serviceTax;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Float getCostWithoutTax() {
		return costWithoutTax;
	}

	public void setCostWithoutTax(Float costWithoutTax) {
		this.costWithoutTax = costWithoutTax;
	}

	public Float getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(Float serviceTax) {
		this.serviceTax = serviceTax;
	}

}
