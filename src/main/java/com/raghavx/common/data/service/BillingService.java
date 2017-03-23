package com.raghavx.common.data.service;

import com.raghavx.common.data.model.Billing;
import com.raghavx.common.data.uo.CreditUO;

/**
 * The Interface BillingService.
 */
public interface BillingService {

	/**
	 * Available credit.
	 *
	 * @param userId the user id
	 * @return the credit UO
	 */
	CreditUO availableCredit(Long userId);

	/**
	 * Deduct credit.
	 *
	 * @param userId the user id
	 * @param creditUO the credit UO
	 */
	void deductCredit(Long userId, CreditUO creditUO);

	/**
	 * Adds the credit.
	 *
	 * @param userId the user id
	 * @param creditUO the credit UO
	 */
	void addCredit(Long userId, CreditUO creditUO);

	/**
	 * Save.
	 *
	 * @param billingEntity the billing entity
	 * @return the billing
	 */
	Billing save(Billing billingEntity);

	
}
