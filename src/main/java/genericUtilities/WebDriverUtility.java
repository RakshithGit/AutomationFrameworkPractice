package genericUtilities;

import java.io.File;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of reusable methods related to Web driver.
 * @author Rakshith
 *
 */
public class WebDriverUtility {
	
	/**
	 * This method will maximize the window.
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will minimize the window.
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will open the window in full screen mode.
	 * @param driver
	 */
	public void fullScreenWindow(WebDriver driver) {
		driver.manage().window().fullscreen();
	}
	
	/**
	 * This method will wait for 10 seconds for all the web elements to load.
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method waits for 10 seconds until the element is visible.
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method waits for 10 seconds for the element to be clickable.
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method handles dropdown using index.
	 * @param element
	 * @param index
	 */
	public void handleDropdown(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * This method will handle the dropdown using the value.
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * This method handles dropdown using visible text.
	 * @param element
	 * @param text
	 */
	public void handleDropdown(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);	
	}
	
	/**
	 * This method will perform mouse hover action on the web element.
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();;
	}
	
	/**
	 * This method is will perform right-click operation on a web page.
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method is will perform right-click operation on a web element.
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This methods performs double click action on the web page.
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver) {
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method performs double click action on the web element.
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method drags and drops a web element from source to target.
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void dragAndDropOption(WebDriver driver, WebElement src, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(src, target);
	}
	
	/**
	 * This method will move the cursor by offset and click.
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void moveCursorAndClick(WebDriver driver, int x, int y) {
		Actions act = new Actions(driver);
		act.moveByOffset(x, y).click().perform();
	}
	
	/**
	 * This method will scroll down by 500 units.
	 * @param driver
	 */
	public void scrollAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
	}
	
	/**
	 * This method will scroll down until a particaular web element is found.
	 * @param driver
	 * @param element
	 */
	public void scrollActions(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	/**
	 * This method accepts alert popup.
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method dismisses alert popup.
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will get the alert text and return it to the caller.
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method handles frame by index.
	 * @param driver
	 * @param index
	 */
	public void handleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle the frame by using name or ID.
	 * @param driver
	 * @param nameOrID
	 */
	public void handleFrame(WebDriver driver, String nameOrID) {
		driver.switchTo().frame(nameOrID);
	}
	
	/**
	 * This method will handle frame using frame element.
	 * @param driver
	 * @param element
	 */
	public void handleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will switch from to it's immediate parent frame.
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will switch to default page.
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will switch the control from one window to another.
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchTowindow(WebDriver driver, String partialWindowTitle) {
		//Capture all the window IDs.
		Set<String> winIDs = driver.getWindowHandles();
		
		//Navigate through each window.
		for (String id : winIDs) {
			//Switch to each window and compare the titles.
			String windowTitle = driver.switchTo().window(id).getTitle();
			//Compare the title with required title.
			if(windowTitle.contains(partialWindowTitle)) {
				break;
			}
		}	
	}
	
	/**
	 * This method captures screenshot and stores it in the required folder.
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws Throwable
	 */
	public String captureScreenshot(WebDriver driver, String screenshotName) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\Screenshots\\"+screenshotName+".png");
		Files.copy(src, dest); //We can also use FileUtils.copyFile().
		return dest.getAbsolutePath(); //Used for Extent Report.
	}
}

