package vtigerpractice;

import org.testng.Assert;
import org.testng.annotations.Test;
//priority
//TestNG consists of @Test annotation which acts as a main method.
//@Test(priority = int)
//The priority of execution follows ASCII value if we don't give any priority.
//The default priority is 0.
//Lowest value gets executed first.
//Negative numbers are acceptable for priority.

//invocationCount
//To run @Test more than once, we use @Test(invocationCount = int).
//The default value of invocationCount is 1.
//It will not accept 0 or negative numbers.
//If 0 or -ve number is given, then that particular @Test will not be considered for the current execution.
//Test Script is disabled.
//If priority and invocationCount are used together, then priority will be given the first preference.

//enabled
//To disable a test script, we use @Test(enabled = false). the default value is true.

//(dependsOnMethods = method_name)
//To make one @Test execution depend on the status of another @Test, 
//we use @Test(dependsOnMethods = "method_name")
public class TestNGPractice {
	
	@Test()
	public void createContact() {
	//Assert.fail();     //To intentionally fail a  method, we use assertions. The methods that are dependent on this method will be skipped.
	System.out.println("Create");
	}
	
	@Test(dependsOnMethods = "createContact")
	public void modifyContact() {
		System.out.println("Modify");
	}
	
	@Test(dependsOnMethods = {"createContact","modifyContact"})
	public void deleteContact() {
		System.out.println("Delete");  
	}
}
