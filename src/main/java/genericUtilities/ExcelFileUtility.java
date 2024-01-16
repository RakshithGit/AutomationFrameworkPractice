package genericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *@author Rakshith
 *This class consists of all the reusable methods related to Excel File. 
 */

public class ExcelFileUtility {
	/**
	 * This method will read the data from the Excel sheet and return the value to the caller.
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 * @throws Throwable
	 */
	
	//rows,columns,value
	public String readDataFromExcel(String sheetName, int rowNumber, int cellNumber ) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		wb.close();
		return value;
		
	}
	
	/**
	 * This method will write data into the specific cell.
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param cellValue
	 * @throws Throwable
	 */
	
	public void writeDataIntoExcel(String sheetName, int rowNumber, int cellNumber, String cellValue ) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet(sheetName).createRow(rowNumber).createCell(cellNumber).setCellValue(cellValue);
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		wb.write(fos);
		wb.close();
		
	}
	
	/**
	 * This method will read multiple data from excel file for the sheet provided.
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public Object[][] readMultipleDataFromExcel(String sheetName) throws Throwable{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[lastRow][lastCell];
		for(int i=0; i<lastRow; i++) {
			for(int j=0; j<lastCell; j++) {
				 data[i][j] = sh.getRow(i+1).getCell(j).getStringCellValue();//If we don't give i+1, then the header will  also be considered.
			}  //We can also initialize i=1 in the for loop. But this is a better approach as we unconsciously write i=0 in the for loop.
		}
		return data;
	}
	
	
	
	
	
	
}
