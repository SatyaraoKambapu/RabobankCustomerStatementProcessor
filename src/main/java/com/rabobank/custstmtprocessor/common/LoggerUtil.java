package com.rabobank.custstmtprocessor.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Used singleton design pattern, It is enough to set all Logger configuration
 * once when instance is created.
 * 
 * @author skambapu
 * 
 */
public class LoggerUtil {
	static Logger logger = Logger.getLogger(LoggerUtil.class.getName());
	private static LoggerUtil instance = null;

	private LoggerUtil() {
		try {
			LogManager.getLogManager().readConfiguration(
					new FileInputStream("./src/main/java/resources/rabobanklogging.properties"));
			logger.setLevel(Level.FINE);
			logger.addHandler(new ConsoleHandler());

			// FileHandler file name with max size and number of log files limit
			Handler fileHandler = new FileHandler("./src/main/java/server.log",
					10 * 1024 * 1024, 10);
			fileHandler.setFormatter(new LoggerFormatter());
			// setting custom filter for FileHandler
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public static LoggerUtil getInstance() {
		if (instance == null) {
			synchronized (LoggerUtil.class) {
				if (instance == null) {
					instance = new LoggerUtil();
				}
			}
		}
		return instance;
	}

	public Logger getLogger() {
		return logger;
	}
}
