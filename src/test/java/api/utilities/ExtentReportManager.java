package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentReportManager implements ITestListener{
	
	
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	
	public void onStart(ITestContext context) {
		
	     extentSparkReporter = new ExtentSparkReporter("Extentreports.html");
	     
	     extentSparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
	     extentSparkReporter.config().setReportName("Go Rest API Automation");
	     extentSparkReporter.config().setTheme(Theme.DARK);
	     
	     extentReports = new ExtentReports();
	     extentReports.attachReporter(extentSparkReporter);
	     extentReports.setSystemInfo("application","Go Rest Automation");
	     extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
	     extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
	     extentReports.setSystemInfo("Environment", "QA");
	     extentReports.setSystemInfo("user","Umashankar");     
	}
	public void onTestSuccess(ITestResult result) 
	{
		extentTest=extentReports.createTest(result.getName());
	    extentTest.assignCategory(result.getMethod().getGroups());
	    extentTest.createNode(result.getName());
	    extentTest.log(Status.PASS,"Test Passed");
		
	}
	public void onTestFailure(ITestResult result) 
	{
		extentTest=extentReports.createTest(result.getName());
	    extentTest.assignCategory(result.getMethod().getGroups());
	    extentTest.log(Status.FAIL,"Test Failed");
	    extentTest.log(Status.FAIL, result.getThrowable().getMessage());
	    
		
	}
	public void onTestSkipped(ITestResult result) 
	{
		extentTest=extentReports.createTest(result.getName());
		extentTest.createNode(result.getName());
	    extentTest.assignCategory(result.getMethod().getGroups());
	    extentTest.log(Status.SKIP,"Test Skipped");
	    extentTest.log(Status.SKIP, result.getThrowable().getMessage());	
	}
	public void  onFinish(ITestContext testcontext) {
		
		extentReports.flush();
		try {
			Desktop.getDesktop().browse(new File("Extentreports.html").toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
