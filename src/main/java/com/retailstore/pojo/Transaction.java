package com.retailstore.pojo;

import java.util.List;

public class Transaction {
	Integer transactionId;
	List<TransactionItem> items;
	Float totalCost;
	Float totalTax;
	Float grandTotal;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public List<TransactionItem> getItems() {
		return items;
	}

	public void setItems(List<TransactionItem> items) {
		this.items = items;
	}

	public Float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	public Float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Float totalTax) {
		this.totalTax = totalTax;
	}

	public Float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Float grandTotal) {
		this.grandTotal = grandTotal;
	}

}