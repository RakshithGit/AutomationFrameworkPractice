package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage { //The class name should mandatorily end with the word "page".
	// This class contains all the web elements present in login page.
	//POM classes are used to prevent us from getting StaleElementReferenceException.
	
	
	//1) Create a separate POM class for every web page.
	
	//2) Identify the web elements using @FindBy, @FindAll, @FindBys
	//Declaration
	@FindBy(name = "user_name")
	private WebElement usernameEdt;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdit;
	
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	//3) Initialize the web elements by creating a constructor of the LoginPage class with the driver reference.
	//Initialization
	public LoginPage(WebDriver driver){ //Don't forget to make the constructor public.
		PageFactory.initElements(driver, this); // It initializes the web elements with the driver reference,
	}											// so that the web elements get the updated web driver reference.	
	
	//4) Use getter method to access the web elements.
	//Utilization
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	/**
	 * This method will login to the application.
	 * @param driver
	 * @param USERNAME
	 * @param PASSWORD
	 */
	//5) Provide business library. It is the Framework Developer's choice.
	//It is a generic method created using web elements present only in the current page.
	public void loginToApp(String USERNAME, String PASSWORD) {
		usernameEdt.sendKeys(USERNAME); //Code is optimized by using POM class.
		passwordEdit.sendKeys(PASSWORD);
		loginBtn.click();
	}

}
