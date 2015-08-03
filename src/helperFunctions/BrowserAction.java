package helperFunctions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BrowserAction{
	
	public static WebDriver driver = null;
	
	public static WebDriver openBrowser(String browser){
		try{
			if(browser.equalsIgnoreCase("FireFox") || browser.equalsIgnoreCase("Mozilla"))
				driver = new FirefoxDriver();
			else if (browser.equalsIgnoreCase("InternetExplorer"))
				driver = new InternetExplorerDriver();
			else if (browser.equalsIgnoreCase("chrome"))
				driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return driver;
	}
	public static void closeBrowser(){
		driver.quit();
	}
	
}