package vtigerpractice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerPractice {
	
	@Test(retryAnalyzer = genericUtilities.RetryAnalyzerImplementation.class) //We give the retry analyzer at the method level not the class level.
	public void sample() {
		Assert.fail();
		System.out.println("Hi");
	}
}
