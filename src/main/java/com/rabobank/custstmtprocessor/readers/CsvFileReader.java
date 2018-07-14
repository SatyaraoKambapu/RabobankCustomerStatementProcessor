package com.rabobank.custstmtprocessor.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rabobank.custstmtprocessor.common.ErrorMessages;
import com.rabobank.custstmtprocessor.entity.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;

/**
 * This is Singleton design pattern, should be initializaed per application only
 * once.
 * 
 * @author skambapu
 * 
 */
public class CsvFileReader {

	private static CsvFileReader instancce = null;

	private CsvFileReader() {
		// private constructor
	}

	public static CsvFileReader getInstance() {
		if (instancce == null) {
			synchronized (CsvFileReader.class) {
				if (instancce == null) {
					instancce = new CsvFileReader();
				}
			}
		}
		return instancce;
	}

	@SuppressWarnings("resource")
	public List<CustomerRecord> processInputFile(File inputF)
			throws BusinessOperationException {
		BufferedReader br;
		String line;
		List<CustomerRecord> list = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(inputF));
			String header = br.readLine(); // Just skipping and consuming the Header of csv file.
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] cols = line.split(",");
				if (cols.length != 6) {
					throw new BusinessOperationException(ErrorMessages.CSV_FILE_COLUMNS);
				}
				CustomerRecord customerRecord = new CustomerRecord();
				customerRecord.setRecord_referenceId(Long.valueOf(cols[0]));
				customerRecord.setAccountNumber(cols[1]);
				customerRecord.setDescription(cols[2]);
				customerRecord.setStartBalance(new BigDecimal(cols[3]));
				customerRecord.setMutation(new BigDecimal(cols[4]));
				customerRecord.setEndBalance(new BigDecimal(cols[5]));
				list.add(customerRecord);
			}
		} catch (FileNotFoundException e1) {
			throw new BusinessOperationException(e1.getMessage(), e1);
		} catch (IOException e) {
			throw new BusinessOperationException(e.getMessage(), e);
		}
		return list;
	}
}
