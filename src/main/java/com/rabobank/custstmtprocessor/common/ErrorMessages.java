package com.rabobank.custstmtprocessor.common;

public class ErrorMessages {
	public static final String IRRELEVANT_FILE = PropertiesManager
			.getInstance().getProperties().getProperty("IRRELEVANT_FILE");

	public static final String NO_RECORDS_FOUND = PropertiesManager
			.getInstance().getProperties().getProperty("NO_RECORDS_FOUND");

	public static final String NO_FILE_PATH = PropertiesManager.getInstance()
			.getProperties().getProperty("NO_FILE_PATH");

	public static final String CSV_FILE_COLUMNS = PropertiesManager
			.getInstance().getProperties().getProperty("CSV_FILE_COLUMNS");

	public static final String NO_CSV = PropertiesManager.getInstance()
			.getProperties().getProperty("NO_CSV");

	public static final String NO_XML = PropertiesManager.getInstance()
			.getProperties().getProperty("NO_XML");
}
