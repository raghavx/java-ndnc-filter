package com.raghavx.ndnc.common.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class TempTableServiceImpl.
 */
@Service
public class TempTableServiceImpl implements TempTableService {
	
	/** The jdbc template. */
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	/** The temp file path. */
	@Value("${ndnc.temp.file.path}")
	private String tempFilePath;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createTable(String tableName) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("CREATE TABLE ").append(tableName).append("(number bigint(10) NOT NULL)");
		jdbcTemplate.execute(queryBuilder.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dropTable(String tableName) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("DROP TABLE IF EXISTS ").append(tableName);
		jdbcTemplate.execute(queryBuilder.toString());		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pushData(String tableName, String filePath) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("LOAD DATA LOCAL INFILE '").append(filePath)
					.append("' INTO TABLE ").append(tableName).append(" FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n'");
		jdbcTemplate.execute(queryBuilder.toString());
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> findAll(String tempTable,String tempColumn,String targetTable,String targetColumn) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select ").append(targetColumn).append(" from ").append(targetTable).append(" target ,").append(tempTable);
		queryBuilder.append(" tmp where target.").append(targetColumn).append(" = tmp.").append(tempColumn);	
				
		List<Long> result = new ArrayList<>();
		
		jdbcTemplate.query(
				queryBuilder.toString(), new Object[] { },
	                (rs, rowNum) -> result.add(rs.getLong(targetColumn))
	        );
		
		return result;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> findAll(String tempTable,String tempColumn,String targetTable,String targetColumn , Map<String,String> andClause) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select ").append(targetColumn).append(" from ").append(targetTable).append(" target ,").append(tempTable);
		queryBuilder.append(" tmp where target.").append(targetColumn).append(" = tmp.").append(tempColumn);	
	
		andClause.forEach((column,value)-> {
			queryBuilder.append(" and ").append(column).append(value);
		});
				
		List<Long> result = new ArrayList<>();
		
		jdbcTemplate.query(
				queryBuilder.toString(), new Object[] { },
	                (rs, rowNum) -> result.add(rs.getLong(targetColumn))
	        );
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public File filterDND(String tempTable) throws IOException {
		
		String filePath = buildTempFilePath("dnd");
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select ").append("phone_number").append(" from ").append("dnd_data").append(" target ,").append(tempTable);
		queryBuilder.append(" tmp where target.").append("phone_number").append(" = tmp.").append("number");
		queryBuilder.append(" and ").append("ops_type='").append("A'");
		queryBuilder.append(" INTO OUTFILE '").append(filePath).append("'");
		queryBuilder.append(" FIELDS TERMINATED BY ','");
		queryBuilder.append(" LINES TERMINATED BY '\n'");	
		
		jdbcTemplate.execute(queryBuilder.toString());
		
		return new File(filePath);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public File filterNonDND(String tempTable) throws IOException {
		
		String filePath = buildTempFilePath("nondnd");
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select ").append("phone_number as phone_number").append(" from ").append("dnd_data").append(" target ,").append(tempTable);
		queryBuilder.append(" tmp where target.").append("phone_number").append(" = tmp.").append("number");
		queryBuilder.append(" and ").append("ops_type='").append("D'");
		queryBuilder.append(" union select number as phone_number from ").append(tempTable);
		queryBuilder.append(" tmp1 where not exists (select * from dnd_data tmp2 ");
		queryBuilder.append(" where tmp2.phone_number = tmp1.number)");
		queryBuilder.append(" INTO OUTFILE '").append(filePath).append("'");
		queryBuilder.append(" FIELDS TERMINATED BY ','");
		queryBuilder.append(" LINES TERMINATED BY '\n'");	
		
		jdbcTemplate.execute(queryBuilder.toString());
		
		return new File(filePath);
	}

	/**
	 * Builds the temp file path.
	 *
	 * @param type the type
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String buildTempFilePath(String type) throws IOException {
		StringBuilder pathBuilder = new StringBuilder();
		Random rnd = new Random();		
		String nextId = Long.toString(2176782336L + rnd.nextInt(900000), 36).substring(1);	
		pathBuilder.append(tempFilePath).append(type).append("-").append(nextId).append("-").append(String.valueOf(System.currentTimeMillis())).append(".csv");
		return pathBuilder.toString();
		
	}
	
}
