package vtigerpractice;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithOrganizationSelect {

	public static void main(String[] args) throws Throwable {

		Random ran = new Random();
		int randomNumber = ran.nextInt(1000);

		// Launch browser with the URL.
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");

		// Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		// Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Click on Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("last" + randomNumber);

		// Select the Organization from organization look up image
		String mainWinID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@title='Select']")).click();

		// Step 7: Switch the control to child window
		Set<String> allWinIDs = driver.getWindowHandles();
		for (String id : allWinIDs) {
			if (!id.contains(mainWinID)) {
				driver.switchTo().window(id);
				System.out.println("window swicthed to child");
				Thread.sleep(1500);
				break;
			}
		}

		// Step 8: search for Organization
		driver.findElement(By.name("search_text")).sendKeys("samplevtiger");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='samplevtiger']")).click();

		// Step 9: Switch the control back to main window
		driver.switchTo().window(mainWinID);

		// Step 10 : save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 11: Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contactHeader.contains("last" + randomNumber)) {
			System.out.println("PASS");
			System.out.println(contactHeader);
		} else {
			System.out.println("FAIL");
		}

		// step 12: logout
		WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(mouseHover).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout successful");

		// Step 13: Close the browser
		driver.quit();

	}

}
