package com.rabobank.custstmtprocessor.factory;

import java.io.File;
import java.util.logging.Logger;

import com.rabobank.custstmtprocessor.common.ErrorMessages;
import com.rabobank.custstmtprocessor.common.FileReader;
import com.rabobank.custstmtprocessor.common.LoggerUtil;
import com.rabobank.custstmtprocessor.common.SupportedFileType;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.readers.CsvFileReader;
import com.rabobank.custstmtprocessor.readers.XmlFileReader;

/**
 * Used Factory design pattern to decide the class creation for both XML and CSV
 * types.
 * 
 * @author skambapu
 * 
 */
public class FileReaderFactory {

	static Logger logger = LoggerUtil.getInstance().getLogger();

	public static FileReader createFileReader(File inputFile)
			throws BusinessOperationException {

		FileReader fileReader = null;
		if (inputFile.isFile()
				&& inputFile.getName().endsWith(
						SupportedFileType.CSV.getFileType())) {
			fileReader = new CsvFileReader();
		} else if (inputFile.isFile()
				&& inputFile.getName().endsWith(
						SupportedFileType.XML.getFileType())) {
			fileReader = new XmlFileReader();
		} else {
			throw new BusinessOperationException(ErrorMessages.IRRELEVANT_FILE);
		}
		logger.info("<fileReader>" + fileReader);
		return fileReader;
	}

}
