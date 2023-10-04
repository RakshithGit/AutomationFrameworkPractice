package vtigerpractice;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {

	public static void main(String[] args) throws Throwable {
		//Open document in java-readable format.
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Create a workbook.
		Workbook wb = WorkbookFactory.create(fis);
		
		//Navigate to required Sheet.
		Sheet sheet = wb.getSheet("Organizations");
		
		//Navigate to the required Row.
		Row row = sheet.getRow(1);
		
		//Navigate to the required Cell.
		Cell cell = row.getCell(2);
		
		//Read the data from the cell.
		String cellData = cell.getStringCellValue();
		System.out.println(cellData);
		
		//Close the workbook.
		wb.close();
		
	}

}
