package Driver;

import java.io.*;
import commonlib.ConfigureLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import commonlib.ReadPropertyValues;
import commonlib.Utility;




public class Driver {
	
	public static WebDriver driver;
	public static String browser;
    public static String baseURL;

	//	Open Browser 
	public static Logger logger = ConfigureLog4j.getConigLogger("");

	public void openBrowser() {
		ReadPropertyValues rpDrv = new ReadPropertyValues();
		try {
            browser = rpDrv.getPropValues("Browser");

        }
        catch (IOException ioe)
        {
            System.out.print(ioe.getMessage());
        }
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				
				String gecoFilePath = rpDrv.getPropValues("GecoFilePath");
				gecoFilePath = Utility.getFullPath(gecoFilePath);
				System.setProperty("webdriver.gecko.driver", gecoFilePath);
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Chrome")) {
				String chromDrvrPath = rpDrv.getPropValues("ChromeDriverPath");
				chromDrvrPath = Utility.getFullPath(chromDrvrPath);
				System.setProperty("webdriver.chrome.driver",
						chromDrvrPath);
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("InternetExplorer")) {
				String ieDrvrPath = rpDrv.getPropValues("IEDriverPath");
				ieDrvrPath = Utility.getFullPath(ieDrvrPath);
				System.setProperty("webdriver.ie.driver",
						ieDrvrPath);
				driver = new InternetExplorerDriver();
			}
			logger.info("Browser Opened");
		
		} catch (WebDriverException wde ) {
			System.out.println(wde.getMessage());
		}
		catch (IOException ioExp)
        {
            System.out.println(ioExp.getMessage());
        }

	}
	
	//Navigate to website
	public void openWebSite() throws IOException
	{
        ReadPropertyValues rpDrv = new ReadPropertyValues();
        baseURL = rpDrv.getPropValues("BaseURL");
		driver.get(baseURL);
		logger.info("Navigated to website");
        driver.manage().window().maximize();
    }
	
    // Close the Browser

	public void closeBrowser() {
		driver.quit();
		logger.info("Browser Closed");
	}
}