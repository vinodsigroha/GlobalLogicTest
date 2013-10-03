package proj.vinod.assignment.code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * Following Assumption has been taken while implementing this program.
 * 1. Share values will be in integer only.
 * 2. If there is redundant highest share value then latest year and month will be shown.
 * 3. Sample file is attached with code.
 * 4. Txt file is there for output.
 * 
 */

public class Main {

	/*
	 * Map which will store final company names based on <Company name, Company Object>
	 * It will always contains the highest share value company object
	 */
	static Map<String, Company> companyMap = new HashMap<String, Company>();

	// Array list to maintain company names
	static List<String> companyList = new ArrayList<String>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		BufferedReader bufferedReader = null;

		final String DELIMITER = ",";

		int lineNo = 1;

		int year;
		String month;

		String line = "";
		try {
			/*
			 * Considering file exists at a specific location(D:) in my case with
			 * name SampleCompanyData.csv.
			 *  BufferedReader used to read csv file
			 *  Reading file using buffered reader
			 */
			bufferedReader = new BufferedReader(new FileReader(
					"D:\\SampleCompanyData.csv"));

			// Reading the file line by line
			while ((line = bufferedReader.readLine()) != null) {
				// Get all tokens available in line
				String[] tokens = line.split(DELIMITER);
				/*
				 * Index initial value is 2 Because Company name will start
				 * from 3rd token as first two tokens are year and month in
				 * header
				 */
				for (int index = 2; index < tokens.length; index++) {
					/*
					 * Line number 1 is the header line containing company names
					 * So is given special treatment 
					 */
					if(lineNo==1){
						// Storing Company names in index based Array List.
						companyList.add(tokens[index]);
					}else{
						// From second line onwards first two tokens will be year and month respectively						
						year = Integer.parseInt(tokens[0]);
						month = tokens[1];
						/*
						 *  Getting current company name from company list array list
						 *  (index-2) is used because data starts from second index and we have 
						 *  stored company names using index 0
						 */
						
						String companyName = companyList.get(index - 2);
						int shareValue = Integer.parseInt(tokens[index]);

						// Preparing record for current company
						Company currentCompanyRecord = new Company(companyName,
								year, month, shareValue);

						/*
						 * Comparing if current company share value is greater than 
						 * existing one in the map			
						 * If so replace map entry with current one			
						 */
						if (currentCompanyRecord.compareTo(companyMap
								.get(companyName)) >= 0) {						
							companyMap.put(companyName, currentCompanyRecord);
						}
					}
				}
				lineNo++;
				tokens = null;
			}

			// Showing final result
			Iterator<String> iterator = companyMap.keySet().iterator();
			while (iterator.hasNext()) {
				Company company = companyMap.get(iterator.next());
				System.out.println(company.getName() + ":" + company.getYear()
						+ ":" + company.getMonth() + ":"
						+ company.getShareValue());
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}
}