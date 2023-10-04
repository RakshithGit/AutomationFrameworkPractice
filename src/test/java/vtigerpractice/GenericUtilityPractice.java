package vtigerpractice;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws Throwable {
		PropertyFileUtility pUtil = new PropertyFileUtility();
		String returnCalledMethodValue = pUtil.readDataFromPropertyFile("username");
		System.out.println(returnCalledMethodValue);
		
		String browserValue = pUtil.readDataFromPropertyFile("browser");
		System.out.println(browserValue);
		
		
		ExcelFileUtility eUtil = new ExcelFileUtility();
		String cellValue = eUtil.readDataFromExcel("Organizations", 7, 3);
		System.out.println(cellValue);
		
		ExcelFileUtility util = new ExcelFileUtility();
		util.writeDataIntoExcel("JustCheckingSheet", 1, 5, "CheckData");
		
	}

}
