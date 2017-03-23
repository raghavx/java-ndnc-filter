package com.raghavx.common.data.uo;

import lombok.Data;

@Data
public class AgregateAnalyticUO {

	private long totalRecords;
	private int totalFiles;
	
	private long totalDNDRecords;
	private long totalNonDndRecords;
}
