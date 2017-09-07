package com.retailstore.dao;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.retailstore.main.OnlineRetailStoreContext;
import com.retailstore.pojo.Category;

public class CategoryDao {

	public Category delete(Integer productId) {
		return OnlineRetailStoreContext.getCategory().remove(productId);
	}

	public Category get(Integer integer) {
		return OnlineRetailStoreContext.getCategory().get(integer);
	}

	public Category update(Category originalCategory) {
		return OnlineRetailStoreContext.getCategory().put(originalCategory.getCategoryId(), originalCategory);
	}

	public Category add(Category category) {
		Set<Integer> keySet = OnlineRetailStoreContext.getCategory().keySet();
		SortedSet<Integer> set = new TreeSet<>();
		for (Integer integer : keySet) {
			set.add(integer);
		}
		Integer last = set.last();
		Integer categoryId = last+1;
		category.setCategoryId(categoryId);
		return OnlineRetailStoreContext.getCategory().put(categoryId, category);
	}

}
