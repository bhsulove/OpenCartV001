package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="LoginData")         //DataProvider 1
	public String[][] getData() throws IOException {
		String path = ".\\testData\\OpenCartV001_LoginData.xlsx"; // taking xl file form testData
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for ExcelUtility

		int total_rows = xlutil.getRowCount("Sheet1");
		int total_cells = xlutil.getCellCount("Sheet1", 1); //cells=columns

		String login_data[][] = new String[total_rows][total_cells]; //creating 2D array which can store rows and cells

		for (int row = 1; row <= total_rows; row++) {// read data from xl ,storing in 2D array

			for (int cells = 0; cells < total_cells; cells++) {
				login_data[row-1][cells] = xlutil.getCellData("Sheet1", row, cells); //1,0
			}

		}
		return login_data;  //returning 2D array
	}
	
	//DataProvider 2
	//DataProvider 3

}
