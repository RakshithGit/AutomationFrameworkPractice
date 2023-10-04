package genericUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class will provide provide implementation to ITestListener interface.
 * @author Rakshith
 *
 */
public class ListenersImplementation implements ITestListener{ //We implement the ITestListener interface here.
//These are the methods we get from ITestListener Interface.
	
	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) { //This result is provided by the testNG as an object. result is an object.
		//It contains details about the test method's name, status, and other related information.// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName(); //Here, we capture the method name.
		System.out.println(methodName+"-------Test Script Execution Started--------");
		 
		//Create a test in Extent Report.
		test = report.createTest(methodName); //We create a test as the report gets to know that there is a test that is being started.
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		//System.out.println(methodName+"-------PASS--------");
		
		//The below line of code is called Log-Level.
		test.log(Status.PASS, methodName+"-------PASS--------"); //This will be logged in the test report so we don't need the above print statement.
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		
		
		String methodName = result.getMethod().getMethodName();
		String screenshotName = methodName+jUtil.getSystemDate();
		
		//System.out.println(result.getThrowable()); //This will capture the exception.  
		//System.out.println(methodName+"-------FAIL--------");
		
		test.log(Status.FAIL, methodName+"-------FAIL--------");//This will be logged in the test report so we don't need the above print statement.
		test.log(Status.INFO, result.getThrowable());//This will be logged in the test report so we don't need the above print statement.
		
//In the below code, we are giving 2 parameters :- the driver reference and the name for the screenshot.
//We don't want to extend the base class to this class as we are not using all the methods of the base class here.
//So, we will create a static reference variable called sDriver in the Base Class and then update it with the driver reference and use it here.
		try {
			String path = wUtil.captureScreenshot(BaseClass.sDriver, screenshotName);
			
			//Attach screenshot into the report.
			test.addScreenCaptureFromPath(path);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getThrowable()); //This will capture the exception.
		
		String methodName = result.getMethod().getMethodName();
		//System.out.println(methodName+"-------SKIPPED--------");
		
		test.log(Status.FAIL, methodName+"-------SKIPPED--------");//This will be logged in the test report so we don't need the above print statement.
		test.log(Status.INFO, result.getThrowable());//This will be logged in the test report so we don't need the above print statement.
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) { //We don't need this method.
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {//We don't need this method.
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("-------Suite Execution Started--------");
		
		//Configure the Extent Reports
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDate()+".html"); //Report with date.
		htmlReport.config().setDocumentTitle("Vtiger Execution Report");
		htmlReport.config().setReportName("Automation Execution Report");
		htmlReport.config().setTheme(Theme.DARK);
		
		report = new ExtentReports(); // 'report' variable is declared globally. This class is used to generate extent report.
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Base Browser", "Firefox");
		report.setSystemInfo("Base URL", "http://localhost:8888/index.php");
		report.setSystemInfo("Reporter Name", "Rakshith");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("-------Suite Execution Finished--------");
		
		//Generate report after execution.
		report.flush();
	}

}
