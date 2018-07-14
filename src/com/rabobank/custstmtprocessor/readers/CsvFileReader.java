package com.rabobank.custstmtprocessor.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rabobank.custstmtprocessor.CustomerRecord;
import com.rabobank.custstmtprocessor.common.SupportedFileType;
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
	public List<CustomerRecord> processInputFile(String inputFilePath)
			throws BusinessOperationException {
		BufferedReader br;
		String line;
		List<CustomerRecord> list = new ArrayList<>();
		try {
			if (inputFilePath == null || "".equals(inputFilePath)) {
				throw new BusinessOperationException(
						"Please provide proper input csv file path.");
			} else if (!inputFilePath.contains(SupportedFileType.csv.toString())) {
				throw new BusinessOperationException(
						"Entered file is not CSV file, Please enter csv file only for statement processor.");
			}
			File inputF = new File(inputFilePath);
			br = new BufferedReader(new FileReader(inputF));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] cols = line.split(",");
				if (cols[0].equals("Reference")) {
					continue;
				}
				if (cols.length != 6) {
					System.err
							.println("Need 6 columns in the CSV file to read");
					return list;
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
