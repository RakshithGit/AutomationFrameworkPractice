package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
	/**
	 * This class contains the reusable methods related to Java
	 * @author Rakshith
	 *
	 */
public class JavaUtility {
	/**
	 * This method returns a random number for every execution..
	 * @return
	 */
	public int getRandomNumber() {
		Random ran = new Random();
		int randomNum = ran.nextInt(1000);
		return randomNum;
	}
	
	/**
	 * This method returns the System Date and Time.
	 * @return
	 */
	public String getSystemDate() {
		Date d = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		String date = simple.format(d);
		return date;
	}
	
}
