package com.raghavx.common.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavx.common.data.model.Billing;
import com.raghavx.common.data.repository.BillingRepository;
import com.raghavx.common.data.service.BillingService;
import com.raghavx.common.data.uo.CreditUO;
import com.raghavx.ndnc.auth.model.User;
import com.raghavx.ndnc.auth.service.UserService;

/**
 * The Class BillingServiceImpl.
 */
@Service
public class BillingServiceImpl implements BillingService{

	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The billing repository. */
	@Autowired
	private BillingRepository billingRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreditUO availableCredit(Long userId) {
		User user = userService.findById(userId);
		CreditUO creditUO = new CreditUO();
		creditUO.setUserId(userId);
		Billing billing = billingRepository.findByUser(user);
		creditUO.setCredit(billing.getAvailableDNDCredit());
		return creditUO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deductCredit(Long userId, CreditUO creditUO) {
		User user = userService.findById(userId);
		Billing billing = billingRepository.findByUser(user);
		billing.setAvailableDNDCredit(billing.getAvailableDNDCredit()-creditUO.getCredit());
		billingRepository.save(billing);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCredit(Long userId, CreditUO creditUO) {
		User user = userService.findById(userId);
		Billing billing = billingRepository.findByUser(user);
		billing.setAvailableDNDCredit(billing.getAvailableDNDCredit()+creditUO.getCredit());
		billingRepository.save(billing);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Billing save(Billing billingEntity) {
		return billingRepository.save(billingEntity);		
	}

	
	
}
