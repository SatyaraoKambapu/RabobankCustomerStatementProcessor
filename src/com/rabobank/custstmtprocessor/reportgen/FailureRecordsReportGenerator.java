package com.rabobank.custstmtprocessor.reportgen;

import java.util.Set;

import com.rabobank.custstmtprocessor.CustomerRecord;

/**
 * This class Job is to generate the records report which are failure.
 * 
 * @author skambapu
 * 
 */
public class FailureRecordsReportGenerator {
	public static void generateFailureRecordsReport(
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
