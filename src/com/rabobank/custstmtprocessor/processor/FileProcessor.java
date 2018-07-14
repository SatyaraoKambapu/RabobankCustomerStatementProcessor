package com.rabobank.custstmtprocessor.processor;

import java.util.List;

import com.rabobank.custstmtprocessor.CustomerRecord;
import com.rabobank.custstmtprocessor.common.SupportedFileType;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.readers.CsvFileReader;
import com.rabobank.custstmtprocessor.readers.XmlFileReader;

public class FileProcessor {

	public static List<CustomerRecord> processFile(String inputFilePath)
			throws BusinessOperationException {
		List<CustomerRecord> list = null;
		if (inputFilePath.contains(SupportedFileType.csv.toString())) {
			list = CsvFileReader.getInstance().processInputFile(inputFilePath);
		} else if (inputFilePath.contains(SupportedFileType.xml.toString())) {
			list = XmlFileReader.getInstance().processInputFile(inputFilePath);
		} else {
			throw new BusinessOperationException(
					"Irrelevant file type!!!, Please enter either xml or csv file path.");
		}
		if (list == null || list.isEmpty()) {
			throw new BusinessOperationException(
					"No records found in the file.");
		}
		return list;
	}
}
