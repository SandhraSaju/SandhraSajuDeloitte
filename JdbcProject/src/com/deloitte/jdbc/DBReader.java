package com.deloitte.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBReader {

	public Map<String, String> dbConfigMap = new HashMap<>();

	public Map<String, String> getConfigMap() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("db.config"));
			Enumeration em = props.propertyNames();
			while (em.hasMoreElements()) {
				String key = (String) em.nextElement();
				dbConfigMap.put(key, props.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dbConfigMap;

	}
}
