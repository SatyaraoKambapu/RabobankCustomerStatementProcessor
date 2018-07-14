package com.rabobank.custstmtprocessor.common;

import java.io.File;
import java.util.List;

import com.rabobank.custstmtprocessor.entity.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;

public interface FileReader {

	public List<CustomerRecord> processInputFile(File inputF)
			throws BusinessOperationException;

}