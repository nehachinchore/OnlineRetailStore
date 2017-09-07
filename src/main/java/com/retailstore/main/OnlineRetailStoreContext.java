package com.retailstore.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.retailstore.pojo.Category;
import com.retailstore.pojo.Product;
import com.retailstore.pojo.Transaction;

import io.dropwizard.configuration.ConfigurationException;

public class OnlineRetailStoreContext {
	private static final Logger log = Logger.getLogger((OnlineRetailStoreContext.class).getPackage().getName());

	OnlineRetailStoreConfiguration config = null;
	private static OnlineRetailStoreContext instance = null;
	private static HashMap<Integer, Product> product = new HashMap<>();
	private static HashMap<Integer, Category> category = new HashMap<>();
	private static HashMap<Integer, Transaction> transactions = new HashMap<>();

	private OnlineRetailStoreContext(OnlineRetailStoreConfiguration config) {
		this.config = config;
	}

	public static OnlineRetailStoreContext get() {
		if (instance == null) {
			log.log(Level.SEVERE, "Application Context is not initialized !");
			throw new RuntimeException("Application Context is not initialized !");
		}

		return instance;
	}

	public static OnlineRetailStoreContext init(OnlineRetailStoreConfiguration configuration)
			throws ConfigurationException, IOException {
		instance = new OnlineRetailStoreContext(configuration);
		Category cat = new Category();
		cat.setCategoryId(1);
		cat.setCategoryType("categoryA");
		cat.setTax(0.1f);
		category.put(1, cat);

		Category cat1 = new Category();
		cat1.setCategoryId(2);
		cat1.setCategoryType("categoryB");
		cat1.setTax(0.2f);
		category.put(2, cat1);

		Category cat2 = new Category();
		cat2.setCategoryId(3);
		cat2.setCategoryType("categoryC");
		cat2.setTax(0f);
		category.put(3, cat2);

		Product prod = new Product();
		prod.setProductId(1);
		prod.setCategory(cat);
		prod.setProductName("maggi");
		prod.setCost(15f);
		product.put(1, prod);

		Product prod1 = new Product();
		prod1.setProductId(2);
		prod1.setCategory(cat);
		prod1.setProductName("makhana");
		prod1.setCost(100f);
		product.put(2, prod1);

		Product prod2 = new Product();
		prod2.setProductId(3);
		prod2.setCategory(cat);
		prod2.setProductName("biscuits");
		prod2.setCost(20f);
		product.put(3, prod2);

		return get();
	}

	public static HashMap<Integer, Product> getProduct() {
		return product;
	}

	public static HashMap<Integer, Category> getCategory() {
		return category;
	}

	public static HashMap<Integer, Transaction> getTransactions() {
		return transactions;
	}

}
