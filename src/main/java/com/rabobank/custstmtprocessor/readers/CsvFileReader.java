package com.rabobank.custstmtprocessor.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.rabobank.custstmtprocessor.common.ErrorMessages;
import com.rabobank.custstmtprocessor.common.LoggerUtil;
import com.rabobank.custstmtprocessor.common.SupportedFileType;
import com.rabobank.custstmtprocessor.entity.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;

/**
 * This is to read the CSV file and convert into Java Objects.
 * 
 * @author skambapu
 * 
 */
public class CsvFileReader implements
		com.rabobank.custstmtprocessor.common.FileReader {

	static Logger logger = LoggerUtil.getInstance().getLogger();

	@SuppressWarnings("resource")
	@Override
	public List<CustomerRecord> processInputFile(File inputF)
			throws BusinessOperationException {
		BufferedReader br;
		String line;
		List<CustomerRecord> list = new ArrayList<>();
		try {
			if (inputF.isFile()
					&& !inputF.getName().endsWith(
							SupportedFileType.CSV.getFileType())) {
				throw new BusinessOperationException(ErrorMessages.NO_CSV);
			}
			br = new BufferedReader(new FileReader(inputF));
			String header = br.readLine(); // Just skipping and consuming the
											// Header of csv file.
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] cols = line.split(",");
				if (cols.length != 6) {
					throw new BusinessOperationException(
							ErrorMessages.CSV_FILE_COLUMNS);
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
		logger.info("<Customer records List size from Csv file>" + list.size());
		return list;
	}
}
