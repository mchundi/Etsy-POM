package helperFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utilities.Constant;
import utilities.ExcelUtils;
import AssertCheck.AssertActions;

public class Registration{
	
	static WebElement registrationLink =null;
	static WebDriver d=null;
	public static void isRegistrationLinkPresent(){
		try{
			registrationLink = d.findElement(By.id("register"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void registerUser(int testCaseRow,WebDriver driver) throws Exception{
		d = driver;
		isRegistrationLinkPresent();
		registrationLink.click();
		WebElement registerElement = d.findElement(By.id("inline-overlay"));
		AssertActions.assertPresence(registerElement);
		
		registerElement = d.findElement(By.id("ext-acct-connect"));
		AssertActions.assertPresence(registerElement);
		
		registerElement = d.findElement(By.id("register-tab"));
		AssertActions.assertPresence(registerElement);
		
		registerElement = d.findElement(By.id("first-name"));
		fillField(registerElement,ExcelUtils.getCellData(testCaseRow, Constant.Col_FirstName));
		
		registerElement = d.findElement(By.id("last-name"));
		fillField(registerElement,ExcelUtils.getCellData(testCaseRow, Constant.Col_LastName));
		
		selectGender(ExcelUtils.getCellData(testCaseRow, Constant.Col_Gender));
		
		registerElement = d.findElement(By.id("email"));
		fillField(registerElement,ExcelUtils.getCellData(testCaseRow, Constant.Col_eMail));
		
		registerElement = d.findElement(By.id("password"));
		fillField(registerElement,ExcelUtils.getCellData(testCaseRow, Constant.Col_Password));
		
		registerElement = d.findElement(By.id("password-repeat"));
		fillField(registerElement,ExcelUtils.getCellData(testCaseRow, Constant.Col_Password));
		
		registerElement = d.findElement(By.id("username"));
		fillField(registerElement,ExcelUtils.getCellData(testCaseRow, Constant.Col_UserName));
		
		registerElement = d.findElement(By.id("etsy_finds"));
		new Actions(d).moveToElement(registerElement).click().perform();
		
		d.findElement(By.cssSelector("input[id='register_button'")).click();
		
	}
	
	public static void fillField(WebElement e, String s){
		e.sendKeys(s);
	}
	public static void selectGender(String s){
		WebElement e = d.findElement(By.cssSelector("input[id='"+s+"']"));
		new Actions(d).moveToElement(e).click().perform();
	}
	
	public static void verifySuccessfulRegistration(){
		WebElement confirmation = d.findElement(By.className("confirm-email-notice"));
		AssertActions.assertPresence(confirmation);
		String confirmationText = confirmation.getText();
		AssertActions.assertText(confirmationText);
	}
}