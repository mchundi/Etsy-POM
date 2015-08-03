package AssertCheck;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.*;



public class AssertActions{
	public static void assertPageTitle(String s,WebDriver driver){
		System.out.println("Asserting Page Title");
		assertEquals(s, driver.getTitle());
	}
	
	public static void assertPresence(WebElement e){
		System.out.println("Asserting Element Presence");
		assertTrue(e.isDisplayed());
	}
	
	public static void assertText(String s){
		System.out.println("Asserting Text");
		assertTrue(s.contains("Your account is unconfirmed"));
	}
}