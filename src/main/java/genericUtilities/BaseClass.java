package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.HomePage;
import objectRepository.LoginPage;

//To perform common actions, we go for base class.

/**
 * This is a generic class consisting of all basic configuration.
 * 
 * @author Rakshith
 *
 */
public class BaseClass {
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public JavaUtility jUtil = new JavaUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();

	public WebDriver driver = null;
	
	public static WebDriver sDriver; // Creating a static variable to get driver reference so that it can be used in 
									 // ListenersImplementation class to capture the screenshot.

	@BeforeSuite(alwaysRun = true) //or we can also give groups = "group_name_given_in_Siute.xml_file"
	public void bsConfig() {
		System.out.println("========= Database Connection successful =========");
	}
	
	
	// Ignore @BeforeTest and @AfterTest for now.
	
	//@Parameters("browser") //We use this only for parallel execution. Comment it out as soon as the work is done.
	//@BeforeTest     //We can give @BeforeTest here for parallel execution.
	@BeforeClass(alwaysRun = true) //We usually comment this line out for parallel execution.
	public void bcConfig(/*String BROWSER*/) throws Throwable {
		//Here, we don't need the below line of code i.e., we don't need to read data for browser from
		//the property file as we are reading it from the SuiteXML file and have parameterized the method.
		//Therefore the BROWSER variable will hold the value from the SuiteXML file and will be used here.
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\rajat\\.cache\\selenium\\geckodriver\\win64\\0.34.0\\geckodriver.exe");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
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
		
		sDriver = driver;  //Updating the driver reference to static variable.
		
		System.out.println(BROWSER+"==== Browser Launched ====");
	}

	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws Throwable {
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME,PASSWORD);
		
		System.out.println("==== Login Successful ====");
	}

	@AfterMethod(alwaysRun = true)
	public void amConfig() throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.logoutOfApp(driver);
		
		System.out.println("==== Logout Successful ====");
	}
	
	//@AfterTest    //We can give @AfterTest here for parallel execution.
	@AfterClass(alwaysRun = true)
	public void acConfig() {
		driver.quit();
		
		System.out.println("==== Browser Closed ====");
	}
	
	@AfterSuite(alwaysRun = true)
	public void asConfig() {
		System.out.println("========= Database Connection closed =========");
	}
	
	
	
}
