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
 * Instantiates a new temp file for clean up.
 */
@Data
@Entity
@Table(name = "TEMP_FILE_FOR_CLEAN_UP" )
public class TempFileForCleanUp {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** The file path. */
	@Column(name="FILE_PATH")
	private String filePath;
	
	/** The dated on. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATED_ON",length = 6)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date datedOn;
	
	/** The deleted. */
	@Column(name="DELETED")
	private boolean deleted;

}
