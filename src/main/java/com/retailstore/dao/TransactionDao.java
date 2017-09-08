package com.retailstore.dao;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.retailstore.main.OnlineRetailStoreContext;
import com.retailstore.pojo.Transaction;
import com.retailstore.pojo.TransactionItem;

public class TransactionDao {

	public void update(List<TransactionItem> transactionItem, Integer transactionId) {
		Transaction transaction = OnlineRetailStoreContext.getTransactions().get(transactionId);
		transaction.getItems().addAll(transactionItem);
		OnlineRetailStoreContext.getTransactions().put(transactionId, transaction);
	}

	public Integer create(List<TransactionItem> transactionItem) {
		Set<Integer> keySet = OnlineRetailStoreContext.getTransactions().keySet();
		Integer transactionId = 0;
		if (!keySet.isEmpty()) {
			SortedSet<Integer> set = new TreeSet<>();
			for (Integer integer : keySet) {
				set.add(integer);
			}
			Integer last = set.last();
			transactionId = last + 1;
		}else{
			transactionId = 1;
		}
		Transaction transaction = new Transaction();
		transaction.setTransactionId(transactionId);
		transaction.getItems().addAll(transactionItem);
		OnlineRetailStoreContext.getTransactions().put(transactionId, transaction);
		return transactionId;

	}

	public Transaction get(Integer transactionId) {
		return OnlineRetailStoreContext.getTransactions().get(transactionId);
	}

}
