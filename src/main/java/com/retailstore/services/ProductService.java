package com.retailstore.services;

import com.retailstore.dao.ProductDao;
import com.retailstore.pojo.Product;

public class ProductService {

	private ProductDao productDao = null;

	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}

	public Boolean delete(Integer productId) {
		Product deleted = productDao.delete(productId);
		if (deleted != null) {
			return false;
		} else {
			return true;
		}
	}

	public Product update(Product product) {
		Product originalProduct = productDao.get(product.getProductId());
		if (product.getCategory() != null) {
			originalProduct.setCategory(product.getCategory());
		}
		if (product.getProductName() != null) {
			originalProduct.setProductName(product.getProductName());
		}
		if (product.getCost() != null) {
			originalProduct.setCost(product.getCost());
		}

		return productDao.update(originalProduct);
	}

	public Product add(Product product) {
		return productDao.add(product);
	}

	public Product get(Integer categoryId) {
		return productDao.get(categoryId);
	}

}
