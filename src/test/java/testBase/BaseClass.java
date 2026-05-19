package testBase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    public BaseClass() {
    }

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        //Loading properties file
        logger = Logger.getLogger(this.getClass().getName());
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        //os
        if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setPlatform(Platform.WIN11);
            if (os.equalsIgnoreCase("windows"))
            {
                dc.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                dc.setPlatform(Platform.MAC);
            }
            else if (os.equalsIgnoreCase("linux"))
            {
                dc.setPlatform(Platform.LINUX);
            }
            else
            {
                throw new RemoteException("Invalid os"+os);
            }

            dc.setBrowserName(br.toLowerCase());
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
            }
        else {
              switch (br.toLowerCase())
            {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("no matching browsers");
                    return;

            }
        }

                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get(p.getProperty("appURL"));  //reading url from properties file
                driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity, Regression, Master"})
    public void teardown() {
        driver.quit();
    }

    public String captureScreen(String testName) throws IOException {

        String timeStamp = new SimpleDateFormat("yy.mm.dd.HH.mm.ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destinationPath = System.getProperty("user.dir") + "/screenshots/" + timeStamp + ".png";
        FileUtils.copyFile(source, new File(destinationPath));
        return destinationPath;
    }

    public String randomString() {
        String generatedstring = RandomStringUtils.randomAlphabetic(5);
        return generatedstring;
    }

    public String randomNumber() {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return (generatedString + "@" + generatedNumber);
    }
}

