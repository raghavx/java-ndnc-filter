CREATE TABLE IF NOT EXISTS dnd_data (
  service_area_code int(4) DEFAULT NULL,
  phone_number bigint(10) NOT NULL,
  preferences int(4) DEFAULT NULL,
  ops_type varchar(1) DEFAULT NULL,
  phone_type varchar(32) DEFAULT NULL,
  PRIMARY KEY (phone_number)		
) ENGINE=MyISAM DEFAULT CHARSET=utf8
  PARTITION BY hash (phone_number) partitions 4;
  
  