package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	// 1) Create a separate POM class for every web page.

	// 2) Identify the web elements using @FindBy, @FindAll, @FindBys
	// Declaration
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLnk;

	// 3) Initialize the web elements by creating a constructor of the HomePage
	// class with the driver reference.
	// Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// 4) Use getter method to access the web elements.
	// Utilization
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	
	public WebElement getContactsLnk() {
		return contactsLnk;
	}
	// 5) Provide business library. It is the Framework Developer's choice.
	// It is a generic method created using web elements present only in the current page.
	/**
	 * This method will perform mouse hover action on the web element and will click on sign out option.
	 * @param driver
	 */
	public void logoutOfApp(WebDriver driver) {
		mouseHoverAction(driver, administratorImg);
		signOutLnk.click();
	}
	/**
	 * This method will click on the organization link.
	 */
	public void clickOnOrganizationLink() {
		organizationLink.click();
	}
	
	/**
	 * This method will click on contact link.
	 */
	public void clickOnContactLink() {
		contactsLnk.click();
	}
}
