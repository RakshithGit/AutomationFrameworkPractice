package vtigerpractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelSheet {

	public static void main(String[] args) throws Throwable {
		//Read the document in java-readable format.
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Create a Workbook.
		Workbook wb = WorkbookFactory.create(fis);
		
		//Navigate to required Sheet.
		Sheet sheet = wb.getSheet("Organizations");//If we want to create a sheet, then we use createSheet().
		
		//Navigate to the required Row.
		Row row = sheet.getRow(1); //If we want to create a row, then we use createRow().
		
		//Create the required Cell.
		Cell cell = row.createCell(3);
		
		//Provide the required data into the cell.
		cell.setCellValue("Hello");
		
		//Open file in java-writable format.
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//This writes the modifications we have done in the virtual workbook to the actual Excel sheet.
		wb.write(fos);
		System.out.println("Data added successfully");
		
		//Close the workbook.
		wb.close();
		
		
		
		
	}

}
