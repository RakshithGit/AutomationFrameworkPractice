package vtigerpractice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustry {

	public static void main(String[] args) throws Throwable {
		Random random = new Random();
		int ranNum = random.nextInt(1000); //We can get random numbers within 1000 by using this Random class.
		
		//Launch the browser.
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		
		//Login to application with valid credentials.
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to Organizations link.
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("Intel"+ranNum);
		
		//Select "Chemicals" in the industry drop down.
		Select sel = new Select(driver.findElement(By.name("industry")));
		sel.selectByValue("Chemicals");
		
		//Save. 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify.
		String orgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgName.contains("Intel"+ranNum)) {
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
		driver.quit();
	}

}
