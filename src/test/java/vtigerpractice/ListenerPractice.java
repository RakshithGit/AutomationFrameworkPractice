package vtigerpractice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(genericUtilities.ListenersImplementation.class) //We use @Listeners annotation and provide fully qualified name with .class extension.
public class ListenerPractice { //No need to implement as we are using listeners.

	@Test
	public void demo() { // TestNG is designed to continue executing test methods even if an assertion failure occurs within a test method.
		Assert.fail(); // This will mark the test as failed.
		System.out.println("Hi"); // This code will still be executed.
	}
	
	@Test(dependsOnMethods = "demo")
	public void demo1() {
		System.out.println("Hello");
	}
}
