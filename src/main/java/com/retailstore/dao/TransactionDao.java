package com.retailstore.dao;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.retailstore.main.OnlineRetailStoreContext;
import com.retailstore.pojo.Transaction;
import com.retailstore.pojo.TransactionItem;

public class TransactionDao {

	public void update(TransactionItem transactionItem, Integer transactionId) {
		Transaction transaction = OnlineRetailStoreContext.getTransactions().get(transactionId);
		transaction.getItems().add(transactionItem);
		OnlineRetailStoreContext.getTransactions().put(transactionId, transaction);
	}

	public Integer create(TransactionItem transactionItem) {
		Set<Integer> keySet = OnlineRetailStoreContext.getTransactions().keySet();
		SortedSet<Integer> set = new TreeSet<>();
		for (Integer integer : keySet) {
			set.add(integer);
		}
		Integer last = set.last();
		Integer transactionId = last + 1;
		Transaction transaction = new Transaction();
		transaction.setTransactionId(transactionId);
		transaction.getItems().add(transactionItem);
		OnlineRetailStoreContext.getTransactions().put(transactionId, transaction);
		return transactionId;

	}

	public Transaction get(Integer transactionId) {
		return OnlineRetailStoreContext.getTransactions().get(transactionId);
	}

}
