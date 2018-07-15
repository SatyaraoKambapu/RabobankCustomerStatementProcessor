package com.rabobank.custstmtprocessor.readers.test;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rabobank.custstmtprocessor.common.FileReader;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.readers.CsvFileReader;

public class CsvFileReaderTest {
	FileReader fileReader = null;

	@Before
	public void setUp() {
		fileReader = new CsvFileReader();
	}

	@After
	public void cleanUp() {

	}

	@Test
	public void testProcessCsvInputFile() throws BusinessOperationException {
		String csvpath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.csv";
		File inputF = new File(csvpath);
		Assert.assertEquals(10, fileReader.processInputFile(inputF).size());
	}

	@Test(expected = BusinessOperationException.class)
	public void testProcessNonCsvInputFile() throws BusinessOperationException {
		String csvpath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.xml";
		File inputF = new File(csvpath);
		fileReader.processInputFile(inputF);
	}

}
