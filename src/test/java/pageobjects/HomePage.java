package pageobjects;

import commonlib.Utility;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomePage {
    final static Logger logger = Logger.getLogger(Utility.class.getName());
    public static void clickOnSelfHelp(WebDriver driver) throws IOException
    {
            Utility.captureScreenShot(driver,"Page_Loaded");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElement(By.linkText("Self Help")).click();
            logger.info("Clicked on the Selp Help Link");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            Utility.captureScreenShot(driver,"SelfHelp");
            int size = driver.findElements(By.tagName("iframe")).size();
            int indexOfFrame = HomePage.returnFrameIndex(driver,size);
            driver.switchTo().frame(indexOfFrame);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement appEmbLinkElm = driver.findElement(By.linkText("How to embed flows in my website or application?"));
            appEmbLinkElm.click();
            Utility.captureScreenShot(driver,"App_Embed");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            logger.info("Link 'How to embed flow is clicked'");
            Utility.captureScreenShot(driver,"Embed_Flow_Link");
            WebElement embdLnk = driver.findElement(By.id("wfx-dashboard-content-embed"));
            embdLnk.click();
            logger.info("Embed element is clicked");
            Utility.captureScreenShot(driver,"Embed_DropDown");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement sliidLink = driver.findElement(By.id("wfx-dashboard-content-embed-slideshow"));
            sliidLink.click();
            logger.info("Option Slide is clicked");
            Utility.captureScreenShot(driver,"Option_Slideshow");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement nextBtn = driver.findElement(By.linkText("next"));
            nextBtn.click();
            logger.info("Next button is clicked");
            Utility.captureScreenShot(driver,"Next_Clicked");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            HomePage.clickOnCloseButton(driver);
            logger.info("Close button is clicked");
    }

    public static void verifyProcessComplted(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        By googlAnl = By.xpath("/html/body/div[5]/div/table/tbody/tr/td[3]/table/tbody/tr[1]/td/div/div/table/tbody/tr[1]/td/div");
        wait.until(ExpectedConditions.presenceOfElementLocated(googlAnl));
        Assert.assertEquals(Utility.isElementVisible(driver,googlAnl,Utility.ElementStatus.VISIBLE),Utility.ElementStatus.VISIBLE);
    }

    private static int returnFrameIndex(WebDriver driver, int noOfIframes)
    {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        Iterator<WebElement> ifs = frames.iterator();
        int desriedFrame=-99;
        for(int i=0; i<noOfIframes; i++) {
            if(ifs.hasNext())
            {
                WebElement iFrame = ifs.next();
                if(iFrame.getAttribute("title").equalsIgnoreCase("Get Started with Whatfix"))
                {
                    desriedFrame = i;
                }
            }
        }
        return desriedFrame;
    }

    private static void clickOnCloseButton(WebDriver driver)
    {
        List<WebElement> allBtns = driver.findElements(By.tagName("button"));
        int noOfButtons = allBtns.size();
        Iterator<WebElement> itrBtns = allBtns.iterator();
        for(int i=0; i<noOfButtons; i++) {
            if(itrBtns.hasNext())
            {
                WebElement button = itrBtns.next();
                if(button.getText().equalsIgnoreCase("CLOSE"))
                {
                    button.click();
                }
            }
        }
    }
}
