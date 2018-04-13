package commonlib;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

public class Utility {

	final static Logger logger = Logger.getLogger(Utility.class.getName());
    public static String getFullPath(String halfPath)
    {
    	String currentDir = System.getProperty("user.dir");
    	currentDir=currentDir.replace("\\", "\\\\");
    	return  currentDir+halfPath;
    }

    public static void captureScreenShot(WebDriver driver,String stepName) throws IOException
    {

    	// Take screenshot and store as a file format
    	ReadPropertyValues rpDrv = new ReadPropertyValues();
    	String scrnShotFldrPath = rpDrv.getPropValues("ScreenshotFolderPath");
    	scrnShotFldrPath = getFullPath(scrnShotFldrPath);
    	 File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);           
    	try 
    	{
    	// now copy the  screenshot to desired location using copyFile method
    	 
    	FileUtils.copyFile(src, new File(scrnShotFldrPath+stepName+System.currentTimeMillis()+".png")); 
    	} 
    	catch (IOException e)
    	{
    	  logger.error(e.getMessage()) ;
    	}
    }


	public static String randomString( int len ){
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ )
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}

    public static String removeLast(String s,int start, int end) {
        if (s!=null && s!="" && s.length()>0) {
            s = s.substring(start,end);
        }
        return s;
    }

    public enum ElementStatus{
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }


    public static ElementStatus isElementVisible(WebDriver driver, By by,ElementStatus getStatus){

        try{
            if(getStatus.equals(ElementStatus.ENABLED)){
                if(driver.findElement(by).isEnabled())

                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;

            }
            if(getStatus.equals(ElementStatus.VISIBLE)){
                if(driver.findElement(by).isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        }catch(org.openqa.selenium.NoSuchElementException nse){
            return ElementStatus.NOTPRESENT;
        }
    }

    public static boolean existsElement(String xpath,WebDriver driver) {
        try {
            driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
