package com.raghavx.common.billing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghavx.common.constant.TransactionType;
import com.raghavx.common.data.service.BillingService;
import com.raghavx.common.data.service.TransactionHistoryService;
import com.raghavx.common.data.uo.CreditUO;
import com.raghavx.ndnc.auth.model.User;

/**
 * The Class CreditController.
 */
@RestController
public class CreditController {
	
	
	/** The transaction history service. */
	@Autowired
	private TransactionHistoryService transactionHistoryService;
	
	/** The billing service. */
	@Autowired
	private BillingService billingService;
	
	
	/**
	 * Available credit.
	 *
	 * @param auth the auth
	 * @return the credit UO
	 */
	@GetMapping("/available/credit")
	public CreditUO availableCredit(Authentication auth) {
		
		User user = null;
		if (auth != null && auth.isAuthenticated()) {
			user = (User) auth.getPrincipal();
		}		
		CreditUO creditUO = billingService.availableCredit(user.getUserId());
		return creditUO;
	}

	/**
	 * Deduct credit.
	 *
	 * @param creditUO the credit UO
	 * @param auth the auth
	 * @param bindingResult the binding result
	 * @return the string
	 */
	@PostMapping("/deduct/credit")
	public String deductCredit(@Valid @RequestBody CreditUO creditUO,Authentication auth,BindingResult bindingResult) {
		
		User user = null;
		if (auth != null && auth.isAuthenticated()) {
			user = (User) auth.getPrincipal();
		}
		billingService.deductCredit(user.getUserId(),creditUO);
		transactionHistoryService.addTransactionHistory(TransactionType.DEBIT, creditUO.getCredit(),user.getUserId());
		return "deduct";

	}

	
	/**
	 * Adds the credit.
	 *
	 * @param creditUO the credit UO
	 * @param auth the auth
	 * @param bindingResult the binding result
	 * @return the string
	 */
	@PostMapping("/add/credit")
	public String addCredit(@Valid @RequestBody CreditUO creditUO,Authentication auth,BindingResult bindingResult) {
		
		User user = null;
		if (auth != null && auth.isAuthenticated()) {
			user = (User) auth.getPrincipal();
		}
		
		billingService.addCredit(user.getUserId(),creditUO);
		transactionHistoryService.addTransactionHistory(TransactionType.CREDIT, creditUO.getCredit(),user.getUserId());
		return "add";

	}
}
