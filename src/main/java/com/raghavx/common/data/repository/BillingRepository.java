package com.raghavx.common.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavx.common.data.model.Billing;
import com.raghavx.ndnc.auth.model.User;

/**
 * The Interface BillingRepository.
 */
@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the billing
	 */
	Billing findByUser(User user);

}
