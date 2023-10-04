package organziationTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CreateNewOrgnaizationsPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateMultileOrgWithIndustry extends BaseClass{
	
	//The below lines of code are not required as we are extending the base class and we inherit these codes from base class.
	
	/*ExcelFileUtility eUtil = new ExcelFileUtility();
	
	PropertyFileUtility pUtil = new PropertyFileUtility();
	JavaUtility jUtil = new JavaUtility();
	WebDriverUtility wUtil = new WebDriverUtility(); */
	
	@Test(dataProvider = "getData")
	public void createMultipleOrg(String ORG, String INDUSTRY) throws Throwable {
		//System.out.println(ORGNAME+"-"+INDUSTRYTYPE); This was just for verification.
		
		//The below lines of code are not required as we are extending the base class and we inherit these codes from base class.
		
		/*WebDriver driver = null;
		
		//Read data from Property file.
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//Read data from Excel file.
		//String ORGNAME = eUtil.readDataFromExcel("Organizations", 4, 2)+jUtil.getRandomNumber();
		//String INDUSTRY = eUtil.readDataFromExcel("Organizations", 4, 3);
		//The above 2 lines are not needed as we are getting the data from dataProvider. */
		
		String ORGNAME = ORG+jUtil.getRandomNumber();
		
		//The below lines of code are not required as we are extending the base class and we inherit these codes from base class.
		
		//Launch the browser. Run-time polymorphism-driver.
		/*if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Invalid browser.");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Provide valid credentials from the property file.
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME,PASSWORD);
		
		//driver.findElement(By.name("user_name")).sendKeys(USERNAME); //User name
				
		//driver.findElement(By.name("user_password")).sendKeys(PASSWORD); //Password
				
		//driver.findElement(By.id("submitButton")).click(); */
				
		//Navigate to Organizations link.
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		//driver.findElement(By.linkText("Organizations")).click(); //Replaced by the above line. 
		
		//Click on create Organization look-up image.
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationLookUpImage();
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click(); //Replaced by the above line.
		
		CreateNewOrgnaizationsPage cnop = new CreateNewOrgnaizationsPage(driver);
		cnop.createNewOrganization(ORGNAME, INDUSTRY);
		//The below lines of code are replaced by the above single line of code.
		//Enter the organization name.
		/*driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Select "Chemicals" in the industry drop down.
		WebElement dropDownName = driver.findElement(By.name("industry"));
		wUtil.handleDropDown(dropDownName, INDUSTRY);
		
		//Save. 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();*/
		
		//Verify.
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgName = oip.getOrgHeader();
		//String orgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();//Replaced by the above line.
		
		//We will use assertions instead of below lines of code.
		/*if(orgName.contains(ORGNAME)) {
			System.out.println("PASS: The Organization has been successfully created and validated.");
			System.out.println("The Organization name is: "+orgName);
		}else {
			System.out.println("FAIL: The Organization has not been successfully created and validated.");
		}*/
		
		Assert.assertTrue(orgName.contains(ORGNAME));
		System.out.println("PASS: The Organization has been successfully created and validated.");
		System.out.println("The Organization name is: "+orgName);
		
		//The below lines of code are not required as we are extending the base class and we inherit these codes from base class.
		/* //Logout of application.
		hp.logoutOfApp(driver);
		Thread.sleep(1500);
		//The below lines of code are replaced by the above single line of code.
		//WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//wUtil.mouseHoverAction(driver, mouseHover);
		//Thread.sleep(1500);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();//Or we can use link text.//Replaced by the above line.
				
		//Close the application.
		Thread.sleep(1500);
		driver.quit(); */
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		
		 Object[][] data = eUtil.readMultipleDataFromExcel("MultipleOrg");
		 return data;
	}
}
