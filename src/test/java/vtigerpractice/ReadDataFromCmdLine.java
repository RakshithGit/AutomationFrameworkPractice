package vtigerpractice;

import org.testng.annotations.Test;

public class ReadDataFromCmdLine {
			//This program shows how data is being written here using the command line by Maven.
	@Test   //We  are reading data here using the command line by Maven.
	public void readData() { //We don't use this approach more often. We use it very rarely.
		String BROWSER = System.getProperty("browser");
		String URL = System.getProperty("url");
		System.out.println(BROWSER); 
		System.out.println(URL);
	}
	
	
	
}
