package com.rabobank.custstmtprocessor.reportgen;

import java.util.Set;
import java.util.logging.Logger;

import com.rabobank.custstmtprocessor.common.LoggerUtil;
import com.rabobank.custstmtprocessor.entity.CustomerRecord;

/**
 * Used Singleton design pattern This class Job is to generate the records
 * report which are failure.
 * 
 * @author skambapu
 * 
 */
public class FailureRecordsReportGenerator {
	static Logger logger = LoggerUtil.getInstance().getLogger();

	private static FailureRecordsReportGenerator instance = null;

	private FailureRecordsReportGenerator() {

	}

	public static FailureRecordsReportGenerator getInstance() {
		if (instance == null) {
			synchronized (FailureRecordsReportGenerator.class) {
				if (instance == null) {
					instance = new FailureRecordsReportGenerator();
				}
			}
		}
		return instance;
	}

	public void generateFailureRecordsReport(
			Set<CustomerRecord> invalidCustomerRecords) {
		if (invalidCustomerRecords.isEmpty()) {
			logger.info("Congrats!! No invalid records found.");
			return;
		}
		// Print the list objects in tabular format.
		System.out
				.println("-----------------------------------------------------------------------------");
		System.out.printf("%20s %50s", "REFERNCE ID", "FAILURE DESCRIPTION");
		System.out.println();
		System.out
				.println("-----------------------------------------------------------------------------");
		for (CustomerRecord customerRecord : invalidCustomerRecords) {
			System.out.format("%20s %50s",
					customerRecord.getRecord_referenceId(),
					customerRecord.getDescription());
			System.out.println();
		}
		System.out
				.println("-----------------------------------------------------------------------------");
	}

}
