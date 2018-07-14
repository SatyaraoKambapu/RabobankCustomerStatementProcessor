package com.rabobank.custstmtprocessor.processor;

import java.util.List;

import com.rabobank.custstmtprocessor.common.ErrorMessages;
import com.rabobank.custstmtprocessor.common.SupportedFileType;
import com.rabobank.custstmtprocessor.entity.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.readers.CsvFileReader;
import com.rabobank.custstmtprocessor.readers.XmlFileReader;

/**
 * This Processor class will decide which file type need to be processed.
 * 
 * @author skambapu
 * 
 */
public class FileProcessor {

	public static List<CustomerRecord> processFile(String inputFilePath)
			throws BusinessOperationException {
		List<CustomerRecord> list = null;
		if (inputFilePath.contains(SupportedFileType.csv.toString())) {
			list = CsvFileReader.getInstance().processInputFile(inputFilePath);
		} else if (inputFilePath.contains(SupportedFileType.xml.toString())) {
			list = XmlFileReader.getInstance().processInputFile(inputFilePath);
		} else {
			throw new BusinessOperationException(ErrorMessages.IRRELEVANT_FILE);
		}
		if (list == null || list.isEmpty()) {
			throw new BusinessOperationException(ErrorMessages.NO_RECORDS_FOUND);
		}
		return list;
	}
}
