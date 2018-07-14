package com.rabobank.custstmtprocessor.processor;

import java.io.File;
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
		if (inputFilePath == null || "".equals(inputFilePath)) {
			throw new BusinessOperationException(ErrorMessages.NO_FILE_PATH);
		}
		File inputF = new File(inputFilePath);
		if (inputF.isFile()
				&& inputF.getName().endsWith(
						SupportedFileType.CSV.getFileType())) {
			list = CsvFileReader.getInstance().processInputFile(inputF);
		} else if (inputF.isFile()
				&& inputF.getName().endsWith(
						SupportedFileType.XML.getFileType())) {
			list = XmlFileReader.getInstance().processInputFile(inputF);
		} else {
			throw new BusinessOperationException(ErrorMessages.IRRELEVANT_FILE);
		}
		if (list == null || list.isEmpty()) {
			throw new BusinessOperationException(ErrorMessages.NO_RECORDS_FOUND);
		}
		return list;
	}
}
