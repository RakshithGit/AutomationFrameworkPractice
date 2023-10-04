package vtigerpractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice{
//Data Providers are used in order to run a @Test annotation multiple times with different data values every time.
	@Test(dataProvider = "getData") //getData is the method name which we want to take the data from.
	public void addProductToCart(String name, String model, int price) {
		System.out.println(name+"-"+model+"-"+price);
	}
	@DataProvider
	public Object[][] getData() { //The return type of data provider will always be a 2 dimension array.
		Object[][] data = new Object[4][3]; // 4 rows, 3 columns.
											//4 sets of data with 3 fields.
		data[0][0] = "Samsung";
		data[0][1] = "A80";
		data[0][2] = 20000;
				
		data[1][0] = "Iphone";
		data[1][1] = "S13";
		data[1][2] = 23000;
		
		data[2][0] = "Vivo";
		data[2][1] = "V25";
		data[2][2] = 25000;
		
		data[3][0] = "Oppo";
		data[3][1] = "O85";
		data[3][2] = 27000;
		
		return data;
	}
}