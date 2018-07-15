package com.rabobank.custstmtprocessor.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rabobank.custstmtprocessor.processor.test.FileProcessorTest;
import com.rabobank.custstmtprocessor.readers.test.CsvFileReaderTest;
import com.rabobank.custstmtprocessor.readers.test.XmlFileReaderTest;
import com.rabobank.custstmtprocessor.validators.test.CustomerRecordValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ FileProcessorTest.class, CsvFileReaderTest.class,
		XmlFileReaderTest.class, CustomerRecordValidatorTest.class })
public class RaboBankTestSuite {
}
