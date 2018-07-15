package com.rabobank.custstmtprocessor.readers;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.rabobank.custstmtprocessor.common.ErrorMessages;
import com.rabobank.custstmtprocessor.common.LoggerUtil;
import com.rabobank.custstmtprocessor.common.SupportedFileType;
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

	static Logger logger = LoggerUtil.getInstance().getLogger();

	@Override
	public List<CustomerRecord> processInputFile(File inputF)
			throws BusinessOperationException {
		CustomerRecords customerRecords = null;
		try {
			if (inputF.isFile()
					&& !inputF.getName().endsWith(
							SupportedFileType.XML.getFileType())) {
				throw new BusinessOperationException(ErrorMessages.NO_XML);
			}
			JAXBContext jaxbContext = JAXBContext
					.newInstance(CustomerRecords.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			customerRecords = (CustomerRecords) unmarshaller.unmarshal(inputF);
		} catch (JAXBException e) {
			logger.severe(e.getMessage());
			throw new BusinessOperationException(e.getMessage(), e);
		}
		List<CustomerRecord> customerRecordsList = customerRecords.getRecords();
		logger.info("<Customer records List size from XML file>"
				+ customerRecordsList.size());
		return customerRecordsList;
	}

}
