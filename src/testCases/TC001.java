package testCases;

import java.util.concurrent.TimeUnit;
import helperFunctions.*;
import utilities.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import AssertCheck.AssertActions;

public class TC001{
	public static WebDriver driver;
	private String testCaseName;
	private static String testCaseAction;
	private static int testCaseRow;
	
	@BeforeTest
	public void beforeMethod() throws Exception{
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		testCaseName = this.toString();
		testCaseName = ExcelUtils.getTestCaseName(this.toString());
		
		testCaseRow = ExcelUtils.getRowContains(testCaseName,Constant.Col_TestCaseName);
		testCaseAction = ExcelUtils.getCellData(testCaseRow, Constant.Col_Action);
		
		driver = BrowserAction.openBrowser("firefox");
		driver.get(Constant.URL);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	
	
	@Test
	public static void main() throws Exception{
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		AssertActions.assertPageTitle("Etsy - Your place to buy and sell all things handmade, vintage, and supplies",driver);
		
		if(testCaseAction.equalsIgnoreCase("Registration")){
			Registration.registerUser(testCaseRow,driver);
			Registration.verifySuccessfulRegistration();
		}
	}
	
	@AfterTest
	public void afterMethod(){
		BrowserAction.closeBrowser();
	}
}