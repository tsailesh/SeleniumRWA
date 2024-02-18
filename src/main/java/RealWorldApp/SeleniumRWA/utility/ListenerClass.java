/**
 * @auther sailesh
 *
 */
package RealWorldApp.SeleniumRWA.utility;

/**
*
* @author:sailesh
*
*/
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import RealWorldApp.SeleniumRWA.base.BaseClass;

public class ListenerClass extends BaseClass implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentLocal = new ThreadLocal<ExtentTest>();

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporterNG.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getMethod().getMethodName());
		extentTest.log(Status.INFO, result.getName() + " start executing");
		extentLocal.set(extentTest); // unique thread id during parallel run
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentLocal.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String destinationScreenshotPath = null;
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			destinationScreenshotPath = CaptureScreenshot.getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		extentLocal.get().addScreenCaptureFromPath(destinationScreenshotPath);
		extentLocal.get().log(Status.INFO, result.getThrowable());
		extentLocal.get().log(Status.FAIL, result.getName() + " got failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentLocal.get().log(Status.INFO, result.getThrowable());
		extentLocal.get().log(Status.SKIP, result.getName() + " got skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Here onFinish on Listener Class");
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "/ExtentReport/extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

