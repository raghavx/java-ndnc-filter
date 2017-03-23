package com.raghavx.common.data.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavx.common.constant.TransactionType;
import com.raghavx.common.data.model.TransactionHistory;
import com.raghavx.common.data.repository.TransactionHistoryRepository;
import com.raghavx.common.data.service.TransactionHistoryService;
import com.raghavx.ndnc.auth.service.UserService;

/**
 * The Class TransactionHistoryServiceImpl.
 */
@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService{

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	
	private UserService userService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTransactionHistory(TransactionType type, long credit,long uid) {
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAmount(credit);
		transactionHistory.setDatedOn(new Date());
		transactionHistory.setType(type);
		transactionHistory.setUser(userService.findById(uid));
		transactionHistoryRepository.save(transactionHistory);
		
	}

	
}
