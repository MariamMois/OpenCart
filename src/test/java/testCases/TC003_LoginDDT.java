package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")

    public void verifying_LoginDDT(String email, String password, String exp) {

        logger.info("*** Starting TC003_LoginDDT ***");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickAccount();
            hp.clickLogin();

            //login
            LoginPage lp = new LoginPage(driver);
            lp.settxtemail(email);
            lp.settxtpassword(password);
            lp.setBtnclick();

            Thread.sleep(3000);
            //my account
            MyAccountPage map = new MyAccountPage(driver);
            boolean targetpage = map.isMyAccountPageExists();

            if (exp.equalsIgnoreCase("Valid"))
            {
                if (targetpage == true)
                {
                    map.Logout();
                    Assert.assertTrue(true);

                }
            else
            {
                Assert.assertTrue(false);
            }
            if (exp.equalsIgnoreCase("Invalid"))
            {
                if (targetpage == false)
                {
                    map.Logout();
                    Assert.assertTrue(false);
                }
            else
            {
                Assert.assertTrue(true);
            }
        }

    }
        } catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("*** Finished TC003_LoginDDT ***");
    }
}
