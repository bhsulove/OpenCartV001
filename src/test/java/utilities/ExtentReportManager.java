package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass_Test;

public class ExtentReportManager implements ITestListener {
	// UI of the report
	public ExtentSparkReporter sparkReporter;
	// populate common info on the report
	public ExtentReports extentReport;
	// creating test case entries in the report and update status of the test
	// methods(p/f/s)
	public ExtentTest extentTest;

	String reportName;

	public void onStart(ITestContext context) {
		/*
		 * SimpleDateFormat date_format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 * Date date = new Date(); String
		 * current_date_timestamp=date_format.format(date);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";

		// Specify the location for reports.html file
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");// Title of the report
		sparkReporter.config().setReportName("OpenCart Functional Testing");// Name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);

		// The below values are obtained dynamically in the runtime
		extentReport.setSystemInfo("Application", "Opencart");
		extentReport.setSystemInfo("Module", "Admin");
		extentReport.setSystemInfo("Sub Module", "Customers");
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os"); // Capturing values from xml dynamically
		extentReport.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extentReport.setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extentReport.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		extentTest = extentReport.createTest(result.getTestClass().getName()); // Create a new entry in the report		
		extentTest.assignCategory(result.getMethod().getGroups());// To display groups in report
		extentTest.log(Status.PASS, result.getName() + " is successfully executed. "); // update status of
																						// pass/fail/skip
	}

	public void onTestFailure(ITestResult result) {
		extentTest = extentReport.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, "Test Case Failed is : " + result.getName());
		extentTest.log(Status.INFO, "Reason :" + result.getThrowable().getMessage());
		// Adding the screenshot for failed test
		try {
			String imgPath = new BaseClass_Test().captureScreen(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReport.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, "Test Skipped is :" + result.getName());
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		// Opening report automatically
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + reportName;
		File extentReportFile = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * try { URL url = new URL("file:///" + System.getProperty("user.dir") +
		 * "\\reports\\" + reportName);
		 * 
		 * // Create Email message 
		 * ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setPopHost("smtp.googlemail.com"); email.setSmtpPort(465);//Depends on Email server(gmail,yahoo etc..)
		 * email.setAuthenticator(new DefaultAuthenticator("training@gmail.com",
		 * "password")); email.setSSLOnConnect(true);
		 * email.setFrom("training@gmail.com"); // Sender
		 * email.setSubject("Test Report"); email.setMsg("Attached Test Report");
		 * email.addTo("team_member@gmail.com"); email.attach(url, "extentreport",
		 * "check report"); email.send(); // send the email } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

	}
}
