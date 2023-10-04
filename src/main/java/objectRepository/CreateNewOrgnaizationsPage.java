package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrgnaizationsPage extends WebDriverUtility{
	// 1) Create a separate POM class for every web page.

	// 2) Identify the web elements using @FindBy, @FindAll, @FindBys
	// Declaration
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;

	@FindBy(name = "industry")
	private WebElement industryDropDown;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// 3) Initialize the web elements by creating a constructor of the CreateNewOrgnaizationsPage
	// class with the driver reference.
	// Initialization
	public CreateNewOrgnaizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// 4) Use getter method to access the web elements.
	// Utilization
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	// 5) Provide business library. It is the Framework Developer's choice.
	// It is a generic method created using web elements present only in the current page.
	/**
	 * This method will create Organization using mandatory fields.
	 * @param ORGNAME
	 */
	public void createNewOrganization(String ORGNAME) {
		orgNameEdt.sendKeys(ORGNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create Organization by handling industry drop down.
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createNewOrganization(String ORGNAME, String INDUSTRY) {
		orgNameEdt.sendKeys(ORGNAME);
		handleDropDown(industryDropDown, INDUSTRY);
		saveBtn.click();
	}
	

}
