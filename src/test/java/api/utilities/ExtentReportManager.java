package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentReports extentReports;
    //public ExtentHtmlReporter extentHtmlReporter;
    public ExtentSparkReporter extentSparkReporter;
    public ExtentTest extentTest;
    String repName;

    /*@BeforeTest(alwaysRun = true)
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/reports/TestReport-" + timeStamp + ".html";

        extentHtmlReporter = new ExtentHtmlReporter(filePath);

        extentHtmlReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        extentHtmlReporter.config().setReportName("Pet Store Users API");
        extentHtmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("Application", "Pest Store Users API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA Environment");
        extentReports.setSystemInfo("User", "Suleyman Cevik");
    }*/


    @BeforeTest(alwaysRun = true)
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());//time stamp
        String filePath = System.getProperty("user.dir") + "/reports/TestReport-" + timeStamp + ".html";
        repName = "TestReport-" + timeStamp + ".html";

        extentSparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report
        extentSparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report
        extentSparkReporter.config().setReportName("Pet Store Users API"); // name of the report
        extentSparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("Application", "Pest Store Users API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("User", "Suleyman Cevik");
    }


    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.createNode(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.createNode(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.SKIP, "Test Skipped");
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @AfterTest(alwaysRun = true)
    public void onFinish(ITestContext testContext) {
        extentReports.flush();
    }
}
