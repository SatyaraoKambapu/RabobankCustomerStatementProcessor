package com.rabobank.custstmtprocessor.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

	private static PropertiesManager instance = null;
	Properties properties = null;

	private PropertiesManager() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"./src/main/java/resources/rabobankcommon.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertiesManager getInstance() {
		if (instance == null) {
			synchronized (PropertiesManager.class) {
				if (instance == null) {
					instance = new PropertiesManager();
				}
			}
		}
		return instance;
	}

	public Properties getProperties() {
		return properties;
	}

}
