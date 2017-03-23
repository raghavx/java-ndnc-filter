package com.raghavx.common.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.raghavx.common.constant.TransactionType;
import com.raghavx.ndnc.auth.model.User;

import lombok.Data;

/**
 * Instantiates a new transaction history.
 */
@Data
@Entity
@Table(name = "TRANSACTION_HISTORY" )
public class TransactionHistory {
	
	/** The txn id. */
	@Id
	@Column(name="TXN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long txnId;
	
	/** The user id. */
	@ManyToOne(optional=false)
    @JoinColumn(name="USER_ID", nullable=false, updatable=false)
	private User user;
		
	/** The amount. */
	@Column(name="AMOUNT")
	private Long amount;
	
	/** The type. */
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE")
	@ColumnDefault("\'DEBIT\'")
    private TransactionType type;
		
	/** The dated on. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATED_ON",length = 6)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date datedOn;
	

}
