package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data Provider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path=".\\testdata\\Opencart_login_data.xlsx";
		
		XlUtility xlutils = new XlUtility(path);
		int totalRows=xlutils.getRowCount("login");
		int totalCols=xlutils.getCellCount("login",1);
		
		String loginData[][]= new String[totalRows][totalCols] ;
		
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCols;j++) {
				loginData[i-1][j]= xlutils.getCellData("login", i, j);
			}
		}
		return loginData;
	}
	
	//DP2
	//DP3
}
