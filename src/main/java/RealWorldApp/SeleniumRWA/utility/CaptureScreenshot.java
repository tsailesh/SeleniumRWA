/**
 * @auther sailesh
 *
 */
package RealWorldApp.SeleniumRWA.utility;

/**
 * 
 */
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import RealWorldApp.SeleniumRWA.base.BaseClass;

public class CaptureScreenshot extends BaseClass{

	static WebDriver driver;

	public static String getScreenshot(String ssName, WebDriver driver) {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + ssName + ".png";
		try {
			FileHandler.copy(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destination;

	}

}

