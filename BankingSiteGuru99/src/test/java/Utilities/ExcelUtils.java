package Utilities;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFCell cell;
	static CellType celltype;

	public ExcelUtils(String excelPath, String excelSheet) 
	{
		try 
			{
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(excelSheet);
			} 
		
		catch (IOException e) 
		{	
			e.printStackTrace();
		}	
	}
	
	
	public int getRowCount() 
	{
		int rowCount = 0;
		rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}
	
	
	public int getColCount() 
	{
		int colCount = 0;
		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		return colCount;
	}


	public String getCellStringData(int rownum, int colnum) 
	{
		String Data = sheet.getRow(rownum).getCell(colnum).getStringCellValue();
		return Data;
	}

	
	public double getCellNumericData(int rownum, int colnum) 
	{
		double Data = sheet.getRow(rownum).getCell(colnum).getNumericCellValue();		
		return Data;
	}
/*
	public double getCellData(int row, int col) 
	{
		cell = row.
		String cellData;
		celltype = cell.getCellType();
		
		switch (celltype) {
		case STRING:
			cellData = getCellStringData(rownum, colnum)
			
			break;

		default:
			break;
		}
		double Data = sheet.getRow(rownum).getCell(colnum).getNumericCellValue();		
		return Data;
	}
*/
}

