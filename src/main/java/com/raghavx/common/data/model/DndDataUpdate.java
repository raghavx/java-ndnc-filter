package com.raghavx.common.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

/**
 * Instantiates a new dnd data update.
 */
@Data
@Entity
@Table(name = "DND_DATA_UPDATE" )
public class DndDataUpdate {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** The total records. */
	@Column(name="TOTAL_RECORDS")
	@ColumnDefault("0")
	private Long totalRecords;
	
	/** The inserted records. */
	@Column(name="INSERTED_RECORDS")
	@ColumnDefault("0")
	private Long insertedRecords;
	
	/** The updated records. */
	@Column(name="UPDATED_RECORDS")
	@ColumnDefault("0")
	private Long updatedRecords;
	
	/** The updated on. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_ON",length = 6)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date updatedOn;   
    
	
}
