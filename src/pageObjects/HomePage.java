package pageObjects;

import helperFunctions.BrowserAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage{
	private static WebElement element = null;
	private static WebDriver driver = BrowserAction.driver;
	
	public static WebElement linkRegistration() throws Exception{
		try{ 
       	 element = driver.findElement(By.xpath(".//*[@id='account']/a"));
            System.out.println("My Account link is found on the Home Page");
       }catch (Exception e){
    	   System.out.println("My Acocunt link is not found on the Home Page");
      		//throw(e);
      	}
      	
		return element;
	}
}