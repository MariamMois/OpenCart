package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyAccountPage extends BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    WebElement msgHeading;

    // LEFT SIDE logout (the one you want)
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logoutLink;


    public boolean isMyAccountPageExists() {
        try {
            wait.until(ExpectedConditions.visibilityOf(msgHeading));
            return msgHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void Logout() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(logoutLink));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", logoutLink);

            // small pause for stability
            Thread.sleep(500);

            // click using JS
            js.executeScript("arguments[0].click();", logoutLink);

        } catch (Exception e) {
            Assert.fail("Logout failed: " + e.getMessage());
        }
    }

    }
