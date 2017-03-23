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
import org.springframework.format.annotation.DateTimeFormat;

import com.raghavx.common.constant.ProcessingStatus;
import com.raghavx.ndnc.auth.model.User;

import lombok.Data;

/**
 * Instantiates a new file.
 */
@Data
@Entity
@Table(name = "USER_FILE" )
public class UserFile {
	
	/** The file id. */
	@Id
	@Column(name="FILE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fileId;
	
	/** The user id. */
	@ManyToOne(optional=false)
    @JoinColumn(name="USER_ID", nullable=false, updatable=false)
	private User user;
	
	/** The raw file path. */
	@Column(name="RAW_FILE_PATH")
	private String rawFilePath;
	
	/** The dnd filtered file path. */
	@Column(name="DND_FILTERED_FILE_PATH")
	private String dndFilteredFilePath;
	
	/** The non dnd filtered file path. */
	@Column(name="NON_DND_FILTERED_FILE_PATH")
	private String nonDndFilteredFilePath;
	
	/** The total numbers. */
	@Column(name="TOTAL_NUMBERS")
	@ColumnDefault("0")
	private Long totalNumbers;
	
	/** The dnd numbers. */
	@Column(name="DND_NUMBERS")
	@ColumnDefault("0")
	private Long dndNumbers;
	
	/** The non dnd numbers. */
	@Column(name="NON_DND_NUMBERS")
	@ColumnDefault("0")
	private Long nonDndNumbers;
	
	/** The status. */
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	@ColumnDefault("\'IN_PROGRESS\'")
    private ProcessingStatus status;
	
	/** The uploaded on. */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPLOADED_ON",length = 6)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date uploadedOn;
	
	/** The updated on. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_ON",length = 6)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date updatedOn;
	

}
