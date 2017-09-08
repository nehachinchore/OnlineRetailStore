package com.retailstore.services;

import java.util.List;

import com.retailstore.dao.TransactionDao;
import com.retailstore.exception.StoreServerException;
import com.retailstore.pojo.Transaction;
import com.retailstore.pojo.TransactionItem;

public class TransactionService {

	private TransactionDao transactionDao;

	public TransactionService(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public Integer createorUpdateTransaction(List<TransactionItem> transactionItem, Integer transactionId)
			throws StoreServerException {
		if (transactionId > 0) {
			transactionDao.update(transactionItem, transactionId);
			return transactionId;
		} else {
			return transactionDao.create(transactionItem);
		}
	}

	public Transaction get(Integer transactionId) throws StoreServerException {
		return transactionDao.get(transactionId);
	}

}
