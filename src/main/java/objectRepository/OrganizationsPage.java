package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	// 1) Create a separate POM class for every web page.

	// 2) Identify the web elements using @FindBy, @FindAll, @FindBys
	// Declaration
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrgLookUpImage;

	// 3) Initialize the web elements by creating a constructor of the OrganizationsPage
	// class with the driver reference.
	// Initialization
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// 4) Use getter method to access the web elements.
	// Utilization
	public WebElement getCreateOrgLookUpImage() {
		return createOrgLookUpImage;
	}
	
	// 5) Provide business library. It is the Framework Developer's choice.
	// It is a generic method created using web elements present only in the current page.
	/**
	 * This method will click on create organization look up image.
	 */
	public void clickOnOrganizationLookUpImage() {
		createOrgLookUpImage.click();
	}

}
