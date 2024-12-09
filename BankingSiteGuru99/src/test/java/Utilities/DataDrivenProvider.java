package Utilities;

import org.testng.annotations.DataProvider;

public class DataDrivenProvider {		
	
	@DataProvider(name = "ValidLoginData")
	public  Object [][] getValidData(){
		
		String ProjectPath = System.getProperty("user.dir");
		String excelFilePath = ProjectPath + "/src/test/resources/Excel/InputData.xlsx";
		String sheetName= "ValidLogin";
		
		Object InData[][] = testData(excelFilePath, sheetName);
		return InData;
	}
	
	@DataProvider(name = "InValidLoginData")
	public  Object [][] getInvalidData(){
		
		String ProjectPath = System.getProperty("user.dir");
		String excelFilePath = ProjectPath + "/src/test/resources/Excel/InputData.xlsx";
		String sheetName= "InValidLogin";
		
		Object InData[][] = testData(excelFilePath, sheetName);
		return InData;
	}
	
	public static Object [][] testData(String excelFilePath, String sheetName)
	{
		/*DataFormatter dataFormatter = new DataFormatter();
		String formattedCellStr = dataFormatter.formatCellValue(cell);*/
		
		ExcelUtils excel = new ExcelUtils(excelFilePath , sheetName);
		
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		Object Data[][] = new Object[rowCount - 1][colCount];
		
		for (int i = 1; i < rowCount ; i++)
		{
			for (int j = 0; j <colCount ; j++)
			{
				
				Data[i -1][j]  = excel.getCellStringData(i, j);
			}
		}
	
		return Data;
	}

}
