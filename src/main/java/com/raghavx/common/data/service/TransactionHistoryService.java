package com.raghavx.common.data.service;

import com.raghavx.common.constant.TransactionType;

/**
 * The Interface TransactionHistoryService.
 */
public interface TransactionHistoryService {

	/**
	 * Adds the transaction history.
	 *
	 * @param debit the debit
	 * @param credit the credit
	 * @param uid the uid
	 */
	void addTransactionHistory(TransactionType debit, long credit,long uid);

	
}
