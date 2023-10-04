package genericUtilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility {
/**
 * This class consists of Generic Methods related to Property File.
 * @author Rakshith
 * @throws Throwable 
 * @param propertyFileKey
 */
	public String readDataFromPropertyFile(String propertyFileKey) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(propertyFileKey);
		return value;
	} 
	
	
	
	
	
	
	
	
	
}
