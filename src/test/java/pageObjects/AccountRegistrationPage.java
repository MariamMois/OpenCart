package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AccountRegistrationPage extends BasePage {
    WebDriver driver;

    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy (xpath = "//input[@id='input-firstname']")
    WebElement txtfirstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtlastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtemail;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtpassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement privacyandpolicybtn;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement comtinuebtn;

    public void  setTxtfirstname(String fname)
        {
            txtfirstname.sendKeys(fname);
        }
    public void setTxtlastname(String lname )
        {
            txtlastname.sendKeys(lname);
        }
    public void setTxtemail(String email)
        {
            txtemail.sendKeys(email);
        }
    public  void setTxtpassword(String pwd)
        {
            txtpassword.sendKeys(pwd);
        }

    public  void clickprivacyandpolicy()
        {
            scrollAndClick(privacyandpolicybtn);
        }
    public void clickComtinuebtn()
        {
              scrollAndClick(comtinuebtn);  //may not work, so should other methods work: js executor or action class or explicitwait or just submit()
        }



}
