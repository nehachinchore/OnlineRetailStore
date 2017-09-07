package com.retailstore.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReaderUtil extends BasePropertyReader{
	private static String fileName = "/protocol_config.properties";
	private static PropertyReaderUtil instance = null;
	private static Logger logger = LoggerFactory.getLogger(PropertyReaderUtil.class);

	public static PropertyReaderUtil getInstance() {
		if (instance == null) {
			try {
				instance = new PropertyReaderUtil();
			} catch (FileNotFoundException e) {
				logger.error("Error occurred while reading protocol_config.properties file, " + e);
			}
		}
		return instance;
	}

	private PropertyReaderUtil() throws FileNotFoundException {
		super(new FileInputStream(System.getProperty("user.dir") + fileName));
	}
	
}
