import java.util.List;
import java.util.Scanner;

import com.rabobank.custstmtprocessor.CustomerRecord;
import com.rabobank.custstmtprocessor.exception.BusinessOperationException;
import com.rabobank.custstmtprocessor.readers.CsvFileReader;
import com.rabobank.custstmtprocessor.readers.XmlFileReader;
import com.rabobank.custstmtprocessor.reportgen.FailureRecordsReportGenerator;
import com.rabobank.custstmtprocessor.validators.CustomerReaderValidator;

public class StmtProcessor {
	public static void main(String[] args) {
		try {
			List<CustomerRecord> list = null;
			Scanner sc = new Scanner(System.in);
			System.out
					.println("Do you want to enter the XML file path, say y/n");
			String decision = sc.nextLine();
			if (decision.matches("y|Y|Yes|YES|yes")) {
				String filePath = sc.nextLine();
				list = XmlFileReader.getInstance().processInputFile(filePath);

			} else if(decision.matches("n|N|No|NO|no")){
				System.out.println("Then, Please Enter atleast CSV file path");
				String filePath = sc.nextLine();
				list = CsvFileReader.getInstance().processInputFile(filePath);
			}

			if (list == null || list.isEmpty()) {
				throw new BusinessOperationException(
						"No records found in the file.");
			}
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
