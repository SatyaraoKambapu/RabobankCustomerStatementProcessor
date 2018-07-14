package com.rabobank.custstmtprocessor.readers;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.rabobank.custstmtprocessor.common.ErrorMessages;
import com.rabobank.custstmtprocessor.entity.CustomerRecord;
import com.rabobank.custstmtprocessor.entity.CustomerRecords;
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

	public List<CustomerRecord> processInputFile(File inputF)
			throws BusinessOperationException {
		CustomerRecords customerRecords = null;
		try {
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
