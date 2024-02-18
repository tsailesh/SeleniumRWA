/*
 * @author : sailesh
 * Base class (Parent class) for all the test classes.
 * 1. Initialize driver
 * 2. Launch browser
 * 3. Quit Driver
 */

package RealWorldApp.SeleniumRWA.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import RealWorldApp.SeleniumRWA.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public LoginPage login;
	public static Properties prop;

	public BaseClass() {
		prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver initializeDriver() {

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		String url = prop.getProperty("baseUrl");
		if (browser.toLowerCase().contains("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browser.contains("headless")) {
				chromeOptions.addArguments("headless");
			}
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().setSize(new Dimension(1400, 900));
		} else if (browser.toLowerCase().contains("ff")) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			if (browser.contains("headless")) {
				firefoxOptions.addArguments("headless");
			}
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().setSize(new Dimension(1400, 900));
		} else if (browser.toLowerCase().contains("safari")) {
			SafariOptions safariOptions = new SafariOptions();
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver(safariOptions);
			driver.manage().window().setSize(new Dimension(1400, 900));
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchBrowser() {
		driver = initializeDriver();
		login = new LoginPage(driver);
		return login;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}

}
