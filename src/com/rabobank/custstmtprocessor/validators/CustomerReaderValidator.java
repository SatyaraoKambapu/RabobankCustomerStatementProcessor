package com.rabobank.custstmtprocessor.validators;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rabobank.custstmtprocessor.CustomerRecord;

public class CustomerReaderValidator {

	Set<CustomerRecord> invalidCustomerRecords = new HashSet<CustomerRecord>();
	public Set<CustomerRecord> getInvalidCustomerRecords() {
		return invalidCustomerRecords;
	}

	public void validate(List<CustomerRecord> list) {
		Set<Long> setRefernces = new HashSet<>();
		for (CustomerRecord customerRecord : list) {
			Long referenceId = customerRecord.getRecord_referenceId();
			boolean isValidRecordRefernce = validateReferences(setRefernces,
					referenceId);
			boolean isValidRecordEndBalance = validateEndBalance(
					customerRecord, referenceId);
			setInvalidRecords(customerRecord, isValidRecordRefernce,
					isValidRecordEndBalance);
		}
	}

	private void setInvalidRecords(CustomerRecord customerRecord,
			boolean isValidRecordRefernce, boolean isValidRecordEndBalance) {
		if (!isValidRecordRefernce) {
			invalidCustomerRecords.add(customerRecord);
		}
		if (!isValidRecordEndBalance) {
			invalidCustomerRecords.add(customerRecord);
		}
	}

	private boolean validateEndBalance(CustomerRecord customerRecord,
			Long referenceId) {
		BigDecimal mutation = customerRecord.getMutation();
		BigDecimal startBalance = customerRecord.getStartBalance();
		BigDecimal endBalance = startBalance.add(mutation);
		if (endBalance.compareTo(customerRecord.getEndBalance()) == 0) {
			System.out.println("Endbalances are good for reference - "
					+ customerRecord.getRecord_referenceId());
			return true;
		} else {
			System.err
					.println("Oh !!!Incorrect endbalance found for the record -"
							+ referenceId);
			return false;
		}
	}

	private boolean validateReferences(Set<Long> setRefernces,
			Long customerRecordReferenceId) {
		if (setRefernces.add(customerRecordReferenceId)) {
			// All references are unique.
			System.out.println("Well! All references are unique.");
			return true;
		} else {
			System.err.println("Duplicate reference found. "
					+ customerRecordReferenceId);
			return false;
		}
	}

}
