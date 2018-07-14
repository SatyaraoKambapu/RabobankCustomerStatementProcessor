package com.rabobank.custstmtprocessor.reportgen;

import java.util.Set;

import com.rabobank.custstmtprocessor.entity.CustomerRecord;
import com.rabobank.custstmtprocessor.readers.CsvFileReader;

/**
 * Used Singleton design pattern This class Job is to generate the records
 * report which are failure.
 * 
 * @author skambapu
 * 
 */
public class FailureRecordsReportGenerator {

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
			System.out.println("Congrats!! No invalid records found.");
			return;
		}
		for (CustomerRecord customerRecord : invalidCustomerRecords) {
			System.out.println("reference Id:"
					+ customerRecord.getRecord_referenceId()
					+ "  failure description : "
					+ customerRecord.getDescription());
		}
	}

}
