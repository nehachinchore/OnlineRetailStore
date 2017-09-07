package com.retailstore.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.retailstore.exception.StoreException;
import com.retailstore.exception.StoreServerException;
import com.retailstore.pojo.Product;
import com.retailstore.pojo.Transaction;
import com.retailstore.pojo.TransactionItem;

public class BillService {

	private static final Logger log = Logger.getLogger((BillService.class).getPackage().getName());
	ProductService productService = null;
	CategoryService categoryService = null;
	TransactionService transactionService = null;

	public BillService(ProductService productService, CategoryService categoryService,
			TransactionService transactionService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.transactionService = transactionService;
	}

	/**
	 * @param transactionId
	 * @return
	 * @throws StoreException
	 */
	public Transaction generateBill(Integer transactionId) throws StoreException {
		if (transactionId <= 0) {
			log.log(Level.SEVERE, "TransactionId is Invalid");
			throw new StoreException("2", "TransactionID is Invalid");
		}
		Transaction transaction;
		try {
			transaction = transactionService.get(transactionId);
			if(transaction == null){
				throw new StoreException("2", "TransactionID is Invalid");
			}
		} catch (StoreServerException e) {
			throw new StoreException("servererror",
					"Failed to generate bill for transaction " + transactionId + " due to " + e);
		}
		Float totalCost = 0f;
		Float totalTax = 0f;
		List<TransactionItem> items = transaction.getItems();
		for (TransactionItem transactionItem : items) {
			Product product = productService.get(transactionItem.getProductId());
			Float productCost = product.getCost() * transactionItem.getProductQuantity();
			Float productTax = productCost * product.getCategory().getTax();
			transactionItem.setServiceTax(productTax);
			transactionItem.setCostWithoutTax(productCost);
			totalCost = totalCost + productCost;
			totalTax = totalTax + productTax;
		}
		transaction.setItems(items);
		transaction.setTotalCost(totalCost);
		transaction.setTotalTax(totalTax);
		transaction.setGrandTotal(totalCost + totalTax);
		return transaction;
	}

	/**
	 * @param transactionItem
	 * @param transactionId
	 * @return
	 * @throws StoreException
	 */
	public Integer createorUpdateNewBill(TransactionItem transactionItem, Integer transactionId) throws StoreException {
		if (transactionItem.getProductId() <= 0) {
			log.log(Level.SEVERE, "Product ID is Invalid");
			throw new StoreException("1", "Product ID is Invalid");
		}
		if (transactionItem.getProductQuantity() <= 0) {
			transactionItem.setProductQuantity(1);
		}
		try {
			return transactionService.createorUpdateTransaction(transactionItem, transactionId);
		} catch (StoreServerException e) {
			log.log(Level.SEVERE,
					"Failed to create or update new bill due to " + e + " for transaction id " + transactionId);
			throw new StoreException("servererror", "");
		}
	}

}
