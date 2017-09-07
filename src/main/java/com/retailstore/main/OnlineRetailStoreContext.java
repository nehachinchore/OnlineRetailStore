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
		Category cat = new Category(1, 0.1f, "categoryA");
		category.put(1, cat);

		Category cat1 = new Category(2, 0.2f, "categoryB");
		category.put(2, cat1);

		Category cat2 = new Category(3, 0f, "categoryC");
		category.put(3, cat2);

		Product prod = new Product(1, 15f, cat, "maggi");
		product.put(1, prod);

		Product prod1 = new Product(2, 100f, cat1, "makhana");
		product.put(2, prod1);

		Product prod2 = new Product(2, 20f, cat2, "biscuits");
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
