package com.retailstore.services;

import com.retailstore.dao.CategoryDao;
import com.retailstore.pojo.Category;

public class CategoryService {

	private CategoryDao categoryDao = null;

	public CategoryService(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public Boolean delete(Integer categoryId) {
		Category deleted = categoryDao.delete(categoryId);
		if (deleted != null) {
			return false;
		} else {
			return true;
		}
	}

	public Category update(Category categoryToBeUpdated) {
		Category originalCategory = categoryDao.get(categoryToBeUpdated.getCategoryId());
		if (categoryToBeUpdated.getCategoryType() != null) {
			originalCategory.setCategoryType(categoryToBeUpdated.getCategoryType());
		}
		if (categoryToBeUpdated.getTax() != null) {
			originalCategory.setTax(categoryToBeUpdated.getTax());
		}
		return categoryDao.update(originalCategory);
	}

	public Category add(Category category) {
		return categoryDao.add(category);
	}

	public Category get(Integer categoryId) {
		return categoryDao.get(categoryId);
	}

}
