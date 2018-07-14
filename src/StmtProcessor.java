import java.util.List;
import java.util.Scanner;

import com.rabobank.custstmtprocessor.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.processor.FileProcessor;
import com.rabobank.custstmtprocessor.reportgen.FailureRecordsReportGenerator;
import com.rabobank.custstmtprocessor.validators.CustomerReaderValidator;

/**
 * This is the Entry point Main class to process the Statement for file types
 * either CSV or XML.
 * 
 * @author skambapu
 * 
 */
public class StmtProcessor {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the file path.");
			String inputFilePath = sc.nextLine();
			List<CustomerRecord> listCustomerRecords = FileProcessor
					.processFile(inputFilePath);
			CustomerReaderValidator validator = new CustomerReaderValidator();
			validator.validate(listCustomerRecords);
			FailureRecordsReportGenerator
					.generateFailureRecordsReport(validator
							.getInvalidCustomerRecords());
		} catch (BusinessOperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
