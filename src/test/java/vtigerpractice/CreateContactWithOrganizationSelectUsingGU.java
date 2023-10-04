package vtigerpractice;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;

public class CreateContactWithOrganizationSelectUsingGU {

	public static void main(String[] args) throws Throwable {
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		WebDriver driver = null;

		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		
		String lastName = eUtil.readDataFromExcel("Contacts", 4, 3)+jUtil.getRandomNumber();
		String orgName = eUtil.readDataFromExcel("Contacts", 4, 2);
		// Launch the browser.
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid browser.");
		}

		// Maximize the browser.
		wUtil.maximizeWindow(driver);
		
		wUtil.waitForPageLoad(driver);

		// Go to desired URL.
		driver.get(URL);

		// Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Click on Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		// Select the Organization from organization look up image
		String mainWinID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@title='Select']")).click();
		Set<String> allWinIDs = driver.getWindowHandles();
		for (String id : allWinIDs) {
			if (!id.contains(mainWinID)) {
				driver.switchTo().window(id);
				Thread.sleep(1500);
				driver.findElement(By.linkText(orgName)).click();
				break;
			}
		}

		// Save and verify
		driver.switchTo().window(mainWinID);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String createdName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (createdName.contains(lastName)) {
			System.out.println("The contact is successfully created and verified.");
		} else {
			System.out.println("The contact was not created.");
		}

		// Logout of application
		Thread.sleep(1500);
		WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, mouseHover);
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click(); // Or we can use link text.

		// Close the application.
		Thread.sleep(1500);
		driver.quit();

	}

}
