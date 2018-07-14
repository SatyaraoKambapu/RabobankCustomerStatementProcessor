package com.rabobank.custstmtprocessor.readers;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.rabobank.custstmtprocessor.CustomerRecord;
import com.rabobank.custstmtprocessor.CustomerRecords;
import com.rabobank.custstmtprocessor.common.SupportedFileType;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;

/**
 * Used singleton design pattern, this is the class to parse the xml into Java
 * objects.
 * 
 * @author skambapu
 * 
 */
public class XmlFileReader {

	private static XmlFileReader instancce = null;

	private XmlFileReader() {
		// private constructor
	}

	public static XmlFileReader getInstance() {
		if (instancce == null) {
			synchronized (XmlFileReader.class) {
				if (instancce == null) {
					instancce = new XmlFileReader();
				}
			}
		}
		return instancce;
	}

	public List<CustomerRecord> processInputFile(String inputFilePath)
			throws BusinessOperationException {
		CustomerRecords customerRecords = null;
		try {
			if (inputFilePath == null || "".equals(inputFilePath)) {
				throw new BusinessOperationException(
						"Please provide proper input xml file path.");
			} else if (!inputFilePath.contains(SupportedFileType.xml.toString())) {
				throw new BusinessOperationException(
						"Entered file is not XML file, Please enter xml file only for statement processor.");
			}
			File inputF = new File(inputFilePath);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(CustomerRecords.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			customerRecords = (CustomerRecords) unmarshaller.unmarshal(inputF);
		} catch (JAXBException e) {
			throw new BusinessOperationException(e.getMessage(), e);
		}
		return customerRecords.getRecords();
	}

}
