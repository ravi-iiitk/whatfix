package testcases;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pageobjects.HomePage;
import Driver.Driver;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class EmbedAnalyticTest {

    @BeforeClass
    public static void initalize() throws IOException
    {
        Driver drv = new Driver();
        drv.openBrowser();
        drv.openWebSite();
    }

    @Test
    public void verifyEmbedProcess() throws BiffException,WriteException,IOException,InterruptedException
    {
        HomePage.clickOnSelfHelp(Driver.driver);
        HomePage.verifyProcessComplted(Driver.driver);

    }

    @AfterClass
    public static void closeBrowser(){
      Driver drv = new Driver();
      drv.closeBrowser();
    }

}
