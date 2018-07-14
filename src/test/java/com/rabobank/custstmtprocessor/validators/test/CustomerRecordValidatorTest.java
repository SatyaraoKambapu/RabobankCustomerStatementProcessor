package com.rabobank.custstmtprocessor.validators.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rabobank.custstmtprocessor.entity.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.processor.FileProcessor;
import com.rabobank.custstmtprocessor.validators.CustomerRecordValidator;

public class CustomerRecordValidatorTest {
	CustomerRecordValidator customerRecordValidator;

	@Before
	public void setUp() {
		customerRecordValidator = new CustomerRecordValidator();
	}

	@After
	public void cleanUp() {

	}

	@Test
	public void testValidateXmlFileFields() throws BusinessOperationException {
		String xmlpath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.xml";
		List<CustomerRecord> customerRecords = FileProcessor
				.processFile(xmlpath);
		customerRecordValidator.validate(customerRecords);
		Assert.assertEquals(2, customerRecordValidator
				.getInvalidCustomerRecords().size());
	}

	@Test
	public void testValidateCsvFileFields() throws BusinessOperationException {
		String csvpath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.csv";
		List<CustomerRecord> customerRecords = FileProcessor
				.processFile(csvpath);
		customerRecordValidator.validate(customerRecords);
		Assert.assertEquals(4, customerRecordValidator
				.getInvalidCustomerRecords().size());
	}

	@Test
	public void testWithoutValidate() throws BusinessOperationException {
		String csvpath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.csv";
		List<CustomerRecord> customerRecords = FileProcessor
				.processFile(csvpath);
		// customerRecordValidator.validate(customerRecords);
		Assert.assertEquals(0, customerRecordValidator
				.getInvalidCustomerRecords().size());
	}
}
