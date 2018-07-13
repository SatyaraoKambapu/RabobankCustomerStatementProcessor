import java.util.List;
import java.util.Scanner;

import com.rabobank.custstmtprocessor.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.readers.CsvFileReader;
import com.rabobank.custstmtprocessor.reportgen.FailureRecordsReportGenerator;
import com.rabobank.custstmtprocessor.validators.CustomerReaderValidator;

public class StmtProcessor {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the file path");
		String filePath = sc.nextLine();
		try {
			List<CustomerRecord> list = CsvFileReader.getInstance()
					.processInputFile(filePath);
			System.out.println(list.size());
			CustomerReaderValidator validator = new CustomerReaderValidator();
			validator.validate(list);
			FailureRecordsReportGenerator
					.generateFailureRecordsReport(validator
							.getInvalidCustomerRecords());
		} catch (BusinessOperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
