package com.retailstore.dao;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.retailstore.main.OnlineRetailStoreContext;
import com.retailstore.pojo.Product;

public class ProductDao {

	public Product delete(Integer productId) {
		return OnlineRetailStoreContext.getProduct().remove(productId);
	}

	public Product get(Integer productId) {
		return OnlineRetailStoreContext.getProduct().get(productId);
	}

	public Product update(Product originalProduct) {
		return OnlineRetailStoreContext.getProduct().put(originalProduct.getProductId(), originalProduct);
	}

	public Product add(Product product) {
		Set<Integer> keySet = OnlineRetailStoreContext.getProduct().keySet();
		SortedSet<Integer> set = new TreeSet<>();
		for (Integer integer : keySet) {
			set.add(integer);
		}
		Integer last = set.last();
		Integer productId = last + 1;
		product.setProductId(productId);
		return OnlineRetailStoreContext.getProduct().put(productId, product);
	}
}
