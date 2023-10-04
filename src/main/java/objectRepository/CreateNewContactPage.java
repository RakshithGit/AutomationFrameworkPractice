package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility{
	// 1) Create a separate POM class for every web page.

	// 2) Identify the web elements using @FindBy, @FindAll, @FindBys
	// Declaration
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
	private WebElement orgLookUpImg;

	@FindBy(id = "search_txt")      //We won't get these elements present in this page without clicking the above look-up img.
									//Therefore, we add these 2 elements in the same page.
	private WebElement orgSearchEdt;

	@FindBy(name = "search")
	private WebElement orgSearchBtn;

	// 3) Initialize the web elements by creating a constructor of the CreateNewContactPage
	// class with the driver reference.
	// Initialization
	public CreateNewContactPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	// 4) Use getter method to access the web elements.
	// Utilization
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getOrgLookUpImg() {
		return orgLookUpImg;
	}

	public WebElement getOrgSearchEdt() {
		return orgSearchEdt;
	}

	public WebElement getOrgSearchBtn() {
		return orgSearchBtn;
	}
	
	// 5) Provide business library. It is the Framework Developer's choice.
	// It is a generic method created using web elements present only in the current page.
	/**
	 * This method will create contact with mandatory field.
	 * @param LASTNAME
	 */
	public void createNewContact(String LASTNAME) {
		lastNameEdt.sendKeys(LASTNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create contact using Organization.
	 * @param LASTNAME
	 * @param ORGNAME
	 * @param driver
	 */
	public void createNewContact(String LASTNAME,String ORGNAME,WebDriver driver) {
		lastNameEdt.sendKeys(LASTNAME);
		orgLookUpImg.click();
		switchTowindow(driver, "Accounts");
		orgSearchEdt.sendKeys(ORGNAME);
		orgSearchBtn.click();
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();//We can also write //a[text()='"+ORGNAME+"']
		/*
		 * The above line of code gives the dynamic xpath of the web element:- ORGNAME.
		 * We cannot use @FindBy to identify a dynamic web element.
		 * That is why we go for driver.findElement().
		 * The reason that ORGNAME is a dynamic web element is that it is created in the runtime of the script.
		 * This is because it is a precondition.
		 * Since there is no ORGNAME initially because when we get new builds from developers, the database will be empty.
		 * Therefore, this ORGNAME must be created when the script is executed. Therefore, it is a dynamic web element.
		 * In the xpath, we are using dynamic xpath and we are providing the ORGNAME that is generated in the runtime.
		 */
		switchTowindow(driver, "Contacts");
		saveBtn.click();
	}
	

}
