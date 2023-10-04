package vtigerpractice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;
		
		//Open document in java-readable format.
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//Create an object of properties class from java.util package
		Properties p = new Properties();
		
		//Load the document into the properties class
		p.load(fis);
		
		//Provide the key and read the value. Run-time polymorphism-driver.
		String browserValue = p.getProperty("browser");
		if(browserValue.equals("chrome")) {
			driver = new ChromeDriver();
		}else if (browserValue.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if (browserValue.equals("edge")) {
			driver = new EdgeDriver();
		}
		
		//Maximize  and open the web page in the browser.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String URL = p.getProperty("url");
		driver.get(URL);
		
		//Provide valid credentials from the property file.
		String usernameValue = p.getProperty("username");
		driver.findElement(By.name("user_name")).sendKeys(usernameValue); //User name
		
		String passwordValue = p.getProperty("password");
		driver.findElement(By.name("user_password")).sendKeys(passwordValue); //Password
		
		driver.findElement(By.id("submitButton")).click();
		
		
		
	}

}
