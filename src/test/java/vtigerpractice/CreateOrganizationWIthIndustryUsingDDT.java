package vtigerpractice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWIthIndustryUsingDDT {

	public static void main(String[] args) throws Throwable {
		
		Random ran = new Random();
		int randomNum = ran.nextInt(1000);
		
		WebDriver driver = null;
		//Read data from Property file.
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(fisp);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		//Read data from Excel file.
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Row row = wb.getSheet("Organizations").getRow(4);
		String ORGNAME = row.getCell(2).getStringCellValue()+randomNum;
		String INDUSTRY = row.getCell(3).getStringCellValue();
		
		//Launch the browser. Run-time polymorphism-driver.
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Invalid browser.");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//Provide valid credentials from the property file.
		driver.findElement(By.name("user_name")).sendKeys(USERNAME); //User name
				
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD); //Password
				
		driver.findElement(By.id("submitButton")).click();
				
		//Navigate to Organizations link.
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Select "Chemicals" in the industry drop down.
		Select sel = new Select(driver.findElement(By.name("industry")));
		sel.selectByValue(INDUSTRY);
		
		//Save. 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify.
		String orgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgName.contains(ORGNAME)) {
			System.out.println("PASS: The Organization has been successfully created and validated.");
		}else {
			System.out.println("FAIL: The Organization has not been successfully created and validated.");
		}
		
		//Logout of application.
		Thread.sleep(1500);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();//Or we can use link text.
				
		//Close the application.
		Thread.sleep(1500);
		
		wb.close();
		driver.quit();
		
		
		
	}

}
