package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	private ExtentReports reports;
	private ExtentSparkReporter spark;
	private ExtentTest extent;
	private String path;
	private String date;
	public void onStart(ITestContext context) {
		path=new AppConfig().getProperty("REPORTPATH");
		date=AppConfig.currentDate();
		StringBuffer sb=new StringBuffer();
		sb.append("/report_");sb.append(date);sb.append(".html");
		spark=new ExtentSparkReporter(path+sb.toString());
		spark.config().setDocumentTitle("RestAssured_Automation");
		spark.config().setReportName("USER_API");
		spark.config().setTheme(Theme.STANDARD);
		
		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("Application","Pet Store User Rest API");
		reports.setSystemInfo("Operating System",System.getProperty("os.name"));
		reports.setSystemInfo("User Name",System.getProperty("user.name"));
		reports.setSystemInfo("Environment","QA");
		reports.setSystemInfo("USER","Kiran Kumar");
		
		System.out.println("On Start METHOD");
	}
	public void onTestSuccess(ITestResult result) {
		extent=reports.createTest(result.getName());
		extent.assignCategory(result.getMethod().getGroups());
		extent.createNode(result.getName());
		extent.log(Status.PASS,"Test Passed");
	}
	public void onTestFailure(ITestResult result) {
		extent=reports.createTest(result.getName());
		extent.assignCategory(result.getMethod().getGroups());
		extent.createNode(result.getName());
		extent.log(Status.FAIL,"Test Failed");
		extent.log(Status.FAIL,result.getThrowable().getMessage());
	}
	public void onTestSkipped(ITestResult result) {
		extent=reports.createTest(result.getName());
		extent.assignCategory(result.getMethod().getGroups());
		extent.createNode(result.getName());
		extent.log(Status.SKIP,"Test Skipped");
		extent.log(Status.SKIP,result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context) {
		reports.flush();
	}
}
