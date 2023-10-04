package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	// 1) Create a separate POM class for every web page.

	// 2) Identify the web elements using @FindBy, @FindAll, @FindBys
	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderText;

	// 3) Initialize the web elements by creating a constructor of the ContactsInfoPage.
	// class with the driver reference.
	// Initialization
	public ContactsInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// 4) Use getter method to access the web elements.
	// Utilization
	public WebElement getContactHeaderText() {
		return contactHeaderText;
	}

	// 5) Provide business library. It is the Framework Developer's choice.
	// It is a generic method created using web elements present only in the current page.
	public String getContactHeader() {
		return contactHeaderText.getText();
	}
}
