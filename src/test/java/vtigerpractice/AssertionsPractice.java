package vtigerpractice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsPractice {
	
	@Test
	public void test1() { //Hard assert will stop the execution at failed assertion line if the assertion fails.
		System.out.println("Hi started"); //Hard assertion is usually used for mandatory fields.
		Assert.assertEquals("a", "b");
		System.out.println("Hi Ended");
	}
	
	@Test
	public void test2() { //Soft assert will not stop the execution if the assertion fails. Instead it logs all the assertion failures to assertAll().
		System.out.println("Hello started");//Soft assertion is usually used for non-mandatory fields.
		SoftAssert sa = new SoftAssert();
		sa.assertEquals("a", "b");
		System.out.println("Hello Ended");
		sa.assertAll();
	}
	
	
	
	
	
	
	
}
