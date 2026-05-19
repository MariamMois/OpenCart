package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import testBase.BaseClass;
import pageObjects.HomePage;

public class TC001_AccountRegistrationPage extends BaseClass {


    @Test(groups = {"Regression","Master"})
    public void verify_account_registration() {
        logger.info("*** starting a TC001_AccountRegistrationPage ***");
        try
        {
        HomePage hp = new HomePage(driver); //invokes poc constructor
        hp.clickAccount();
        logger.info("clicked on MyAccount link");


        hp.clickRegister();
        logger.info("click on Registration link");

        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

        logger.info("providing customer details");
        regpage.setTxtfirstname(randomString());
        regpage.setTxtlastname(randomString());
        regpage.setTxtemail(randomString() + "@gmail.com");
        regpage.setTxtpassword(randomPassword());

        logger.info("filled registration form");

        regpage.clickprivacyandpolicy();
        logger.info("accepted p/p");
        regpage.clickComtinuebtn();
        logger.info("accepted");

        Assert.assertTrue(driver.getCurrentUrl().contains("account"));
        logger.info("validating message");

    }
        catch (Exception e)
        {
           e.printStackTrace();
           Assert.fail(e.getMessage());
        }
        logger.info("*** TC001_AccountRegistrationPage finished *** ");


    }
    public String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(8)+ "@1";

    }

}


