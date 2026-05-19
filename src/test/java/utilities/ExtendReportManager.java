package utilities;

import java.text.SimpleDateFormat;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExtendReportManager implements ITestListener {
    public ExtentSparkReporter SparkReporter;            //ui reports
    public ExtentReports extent;                         //populate common info in report
    public ExtentTest test;                              // creating t.c. updating methods

    String repName;

    @Override
    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());

        repName = "Test-Report" + timeStamp + ".html";
        SparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);  //specific location

        SparkReporter.config().setDocumentTitle("Opencart Automation Report");
        SparkReporter.config().setReportName("Opencart Functional testing");
        SparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(SparkReporter);
        extent.setSystemInfo("Application","Opencart");
        extent.setSystemInfo("module name","Admin");
        extent.setSystemInfo("Sub module","Customers");
        extent.setSystemInfo("User name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");

        String os = context.getCurrentXmlTest().getParameter("os");             //passing params from xml file
        extent.setSystemInfo("Operating System", os);

        String browser = context.getCurrentXmlTest().getParameter("browser");   //passing params from xml file
        extent.setSystemInfo("Browser", browser);

        List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includeGroups.isEmpty()){
            extent.setSystemInfo("Groups", includeGroups.toString());
        }
    }
    @Override
    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());                                                      //create new entry in report
        test.assignCategory(result.getMethod().getGroups());                                             //to display groups in report
        test.log(Status.PASS, "test case passed"+result.getName()+ "Got successfully executed");  //update status p/f/s
    }
    @Override
    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,result.getName()+"Test case failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());

        try
        {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }
    @Override
    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,result.getName()+"Test Skipped");
    }
    @Override
    public void onFinish(ITestContext testContext){
        extent.flush();

    /*    String pathOfExtendReport = System.getProperty("user.dir"+"\\reports\\"+repName);   // opening the browser url automatically without opening and clicking manually
        File extentReport = new File(pathOfExtendReport);

        try
        {
            Desktop.getDesktop().browse(extentReport.toURI());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }   */
/*
        try {

            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
            ImageHtmlEmail email = new ImageHtmlEmail();

            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("mariammoistsrapishvili333@gmail.com", "password"));
            email.setSSLOnConnect(true);

            email.setFrom("pavanoltraining@gmail.com"); // Sender
            email.setSubject("Test Results");
            email.setMsg("Please find attached report...");
            email.addTo("mariammoistsrapishvili333@gmail.com"); // Receiver

            email.attach(url, "extent report", "please check report...");
            email.send(); // send the email to receiver

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

 */
    }
}



