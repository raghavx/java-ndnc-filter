package com.raghavx.common.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavx.common.data.model.TransactionHistory;

/**
 * The Interface TransactionHistoryRepository.
 */
@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

}
