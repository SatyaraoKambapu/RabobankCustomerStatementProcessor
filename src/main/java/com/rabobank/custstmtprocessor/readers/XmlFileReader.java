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
 * This is the class to parse the xml into Java objects.
 * 
 * @author skambapu
 * 
 */
public class XmlFileReader implements
		com.rabobank.custstmtprocessor.common.FileReader {

	@Override
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
