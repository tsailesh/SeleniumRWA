/**
 * @auther sailesh
 *
 */
package RealWorldApp.SeleniumRWA.utility;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import RealWorldApp.SeleniumRWA.base.BaseClass;

public class ExtentReporterNG extends BaseClass {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReport = new ExtentReports();

		File extentReportFile = new File(System.getProperty("user.dir") + "/ExtentReport/extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Seleniun Framework Automation Report");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);
/*
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir") + "/Configuration/config.properties");

		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		*/

		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReport;
	}
}
