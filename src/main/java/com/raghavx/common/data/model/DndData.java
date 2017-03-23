package com.raghavx.common.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Instantiates a new dnd data.
 */
@Data
@Entity
@Table(name = "DND_DATA" )
public class DndData {

	/** The phone number. */
	@Id
	@Column(name="PHONE_NUMBER")
	private Long phoneNumber;
	
	/** The service area code. */
	@Column(name="SERVICE_AREA_CODE")
	public String serviceAreaCode;
	
	/** The preferences. */
	@Column(name="PREFERENCES")
    public String preferences;
	
	/** The ops type. */
	@Column(name="OPS_TYPE")
    public String opsType;
	
	/** The phone type. */
	@Column(name="PHONE_TYPE")
    public String phoneType;    
    
	
}
