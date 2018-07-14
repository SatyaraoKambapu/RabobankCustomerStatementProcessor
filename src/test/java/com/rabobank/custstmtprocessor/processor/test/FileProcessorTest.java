package com.rabobank.custstmtprocessor.processor.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.processor.FileProcessor;

public class FileProcessorTest {

	@Before
	public void setUp() {

	}

	@After
	public void cleanUp() {

	}

	@Test
	public void testXmlFilePath() throws BusinessOperationException {
		String xmlpath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.xml";
		Assert.assertEquals(10, FileProcessor.processFile(xmlpath).size());
	}

	@Test
	public void testCsvFilePath() throws BusinessOperationException {
		String csvpath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.csv";
		Assert.assertEquals(10, FileProcessor.processFile(csvpath).size());
	}
	
	@Test
	(expected = BusinessOperationException.class)
	public void testNonCsvXmlFilePath() throws BusinessOperationException {
		String filepath = ".\\src\\test\\java\\com\\rabobank\\custstmtprocessor\\resources\\test\\records.txt";
		FileProcessor.processFile(filepath);
	}

}
