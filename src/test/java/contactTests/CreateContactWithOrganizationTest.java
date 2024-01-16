package contactTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrgnaizationsPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

@Listeners(genericUtilities.ListenersImplementation.class) //For Listeners, we provide the annotation at the class level.
public class CreateContactWithOrganizationTest extends BaseClass{
	//We use groups to execute a group of scripts that are to be involved in a similar task.
	//For example, we are using the below script for smoke testing.
	//We create a suite xml file and we provide the group name in the suite xml file.
	//We then use the same name and provide it to the script's @Test annotation whichever needs to be executed.
	//We also provide the same name in the base class to all the annotations present in the base class.
	//Or else, we can also use (alwaysRun = true) in the base class for all the annotations. This will execute the annotations regardless of the group name we provide.
	//This means that no matter what the group name is, the annotations will get executed.
	//Therefore, the control will check for all the annotations that consist of the group name in them. 
	
	@Test(groups = "SmokeSuite") //For group execution, the jvm looks only for annotations that contain groups keyword.
	//Therefore, even if we have another @Test annotation, only the @Test that has group keyword is executed.
	public void createContactWithOrganizationTest() throws Throwable {
		//The below lines of code are not required as we are extending the base class and we inherit these codes from base class.
		
		/*PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		WebDriver driver = null;
		
		//Read data from Property file.
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");  */
		
		//Read data from Excel file. 
		String ORGNAME = eUtil.readDataFromExcel("Contacts", 4, 2)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcel("Contacts", 4, 3);
		wUtil.waitForPageLoad(driver);
		
		//Launch the browser. Run-time polymorphism-driver.
		
		//The below lines of code are not required as we are extending the base class and we inherit these codes from base class.
		
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
		lp.loginToApp(USERNAME,PASSWORD); */
		
		//driver.findElement(By.name("user_name")).sendKeys(USERNAME); //User name
				
		//driver.findElement(By.name("user_password")).sendKeys(PASSWORD); //Password
				
		//driver.findElement(By.id("submitButton")).click();
				
		//Navigate to Organizations link.
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		Reporter.log("Clicked on Organizations Link"); //We do low-level reporting here like this.
		//driver.findElement(By.linkText("Organizations")).click(); //Replaced by the above line. 
		
		//Click on create Organization look-up image.
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationLookUpImage();
		Reporter.log("Clicked on Organizations Lookup Image");
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click(); //Replaced by the above line.
		
		//Create Organization with with mandatory information.
		CreateNewOrgnaizationsPage cnop = new CreateNewOrgnaizationsPage(driver);
		cnop.createNewOrganization(ORGNAME); //We don't need industry drop down here. We just need a simple organization to be created.
		//The below lines of code are replaced by the above single line of code.
		Reporter.log("Organization created");
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
		} */
		
		Assert.assertTrue(orgName.contains(ORGNAME));
		System.out.println("PASS: The Organization has been successfully created and validated.");
		System.out.println("The Organization name is: "+orgName);
		
		//Assert.fail(); //Just to check if screenshot is captured when the script fails.
		
		//Navigate to Contacts link. 
		hp.clickOnContactLink();
		Reporter.log("Clicked on Contacts Link"); 
		
		//Click on Create Contact look up image.
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();
		Reporter.log("Clicked on Contacts Lookup Image");
		
		//Create new contact using the organization.
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME, ORGNAME, driver);
		Reporter.log("Contact created");
		
		//Validate.
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactHeader = cip.getContactHeader();
		
		//We will use assertions instead of below lines of code.
		
		/*if(contactHeader.contains(LASTNAME)) {
			System.out.println("PASS");
			System.out.println(contactHeader);
		}else {
			System.out.println("FAIL");
		}*/
		
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
		
		//The below lines of code are not required as we are extending the base class and we inherit these codes from base class.
		
		//Sign Out of the application.
		/*hp.logoutOfApp(driver);
		
		//Close the browser.
		driver.quit();*/
	}
	
	@Test       //Just to check the graphical representation of 2 test annotations in extent reports, we are creating this method.
	public void demo() {
		System.out.println("Demo");
	}

}
