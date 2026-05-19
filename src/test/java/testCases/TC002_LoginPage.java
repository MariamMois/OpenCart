package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;


public class TC002_LoginPage extends BaseClass {
    @Test(groups = {"Sanity","Master"})
    public void verifyingLoginpage()
    {
        logger.info("*** starting verifying Login page ***");
        try {
            //home page
            HomePage hp = new HomePage(driver);
            hp.clickAccount();
            hp.clickLogin();

            //login
            LoginPage lp = new LoginPage(driver);
            lp.settxtemail(p.getProperty("email"));
            lp.settxtpassword(p.getProperty("password"));
            lp.setBtnclick();

            //my account
            MyAccountPage map = new MyAccountPage(driver);
            boolean targetpage = map.isMyAccountPageExists();

            Assert.assertTrue(targetpage);
        }
        catch (Exception e)
        {
        Assert.fail();
        }
        logger.info("*** TC002_LoginPage finished ");
    }

}
