package RealWorldApp.SeleniumRWA.actions;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import RealWorldApp.SeleniumRWA.actionInterface.ActionInterface;
import RealWorldApp.SeleniumRWA.base.BaseClass;

public class Action extends BaseClass implements ActionInterface {

	public String getText(WebElement element) {
		return element.getText();
	}

	/**
	 * Scroll to element visible
	 * 
	 * @param WebDriver
	 * @param WebElement
	 * 
	 */
	@Override
	public void scrollByVisibilityOfElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	/**
	 * Click WebElement
	 * 
	 * @param WebDriver
	 * @param WebElemet
	 */
	@Override
	public void click(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	/**
	 * Find WebElement
	 * 
	 * @param WebDriver
	 * @param WebElement
	 * @return flag
	 */
	@Override
	public boolean findElement(WebDriver driver, WebElement element) {
		boolean flag = false;
		try {
			element.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Check weather WebElement is displayed or not
	 * 
	 * @param WebDriver
	 * @param WebElement
	 * @return flag
	 */
	@Override
	public boolean isDisplayed(WebElement element) {
		boolean flag = findElement(driver, element) ? true : false;
		if (flag) {
			flag = element.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}

	/**
	 *
	 */
	@Override
	public boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				System.out.println("The element is Selected");
			} else {
				System.out.println("The element is not Selected");
			}
		} else {
			System.out.println("Not selected ");
		}
		return flag;
	}

	/**
	 *
	 */
	@Override
	public boolean isEnabled(WebDriver driver, WebElement element) {
		boolean flag = false;
		flag = findElement(driver, element);
		if (flag) {
			flag = element.isEnabled();
			if (flag) {
				System.out.println("The element is Enabled");
			} else {
				System.out.println("The element is not Enabled");
			}
		} else {
			System.out.println("Not Enabled ");
		}
		return flag;
	}

	/**
	 *
	 */
	@Override
	public boolean type(WebElement element, String text, Keys keys) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			element.clear();
			if (text != null) {
				element.sendKeys(text);
			}else {
				element.sendKeys(keys);
			}
			flag = true;
		} catch (Exception e) {
			System.out.println("Element Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully send value");
			} else {
				System.out.println("Unable to send value");
			}

		}
		return flag;
	}

	/**
	 *
	 */
	@Override
	public boolean selectBySendkeys(String value, WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from the DropDown");
			} else {
				System.out.println("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}

	@Override
	public boolean selectByIndex(WebElement element, int index) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	@Override
	public boolean selectByValue(WebElement element, String value) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}

	@Override
	public boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean mouseHoverByJavaScript(WebElement ele) {
		boolean flag = false;
		try {
			WebElement mo = ele;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("MouseOver Action is performed");
			} else {
				System.out.println("MouseOver Action is not performed");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean JSClick(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				System.out.println("Click Action is performed");
			} else if (!flag) {
				System.out.println("Click Action is not performed");
			}
		}
		return flag;
	}

	/**
	 *
	 */
	@Override
	public boolean switchToFrameByIndex(WebDriver driver, int index) {
		int timeout = 15; // Set from config file
		boolean flag = false;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(timeout))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with index \"" + index + "\" is selected");
			} else {
				System.out.println("Frame with index \"" + index + "\" is not selected");
			}
		}
	}

	@Override
	public boolean switchToFrameById(WebDriver driver, String idValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Id \"" + idValue + "\" is selected");
			} else {
				System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	@Override
	public boolean switchToFrameByName(WebDriver driver, String nameValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean switchToDefaultFrame(WebDriver driver) {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				// SuccessReport("SelectFrame ","Frame with Name is selected");
			} else if (!flag) {
				// failureReport("SelectFrame ","The Frame is not selected");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public void mouseOverElement(WebDriver driver, WebElement element) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println(" MouserOver Action is performed on ");
			} else {
				System.out.println("MouseOver action is not performed on");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean moveToElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			// actions.moveToElement(driver.findElement(locator)).build().perform();
			actions.moveToElement(ele).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 *
	 */
	@Override
	public boolean mouseover(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(ele).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			/*
			 * if (flag) {
			 * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
			 * +"\""); } else {
			 * failureReport("MouseOver","MouseOver action is not performed on \""
			 * +locatorName+"\""); }
			 */
		}
	}

	/**
	 *
	 */
	@Override
	public boolean draggable(WebDriver driver, WebElement source, int x, int y) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(source, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {

			return false;

		} finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \"" + source + "\"");
			} else if (!flag) {
				System.out.println("Draggable action is not performed on \"" + source + "\"");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean draganddrop(WebDriver driver, WebElement source, WebElement target) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if (!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean slider(WebDriver driver, WebElement ele, int x, int y) {
		boolean flag = false;
		try {
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Slider Action is performed");
			} else {
				System.out.println("Slider Action is not performed");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean rightclick(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			Actions clicker = new Actions(driver);
			clicker.contextClick(ele).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("RightClick Action is performed");
			} else {
				System.out.println("RightClick Action is not performed");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count - 1]);

			if (driver.getTitle().contains(windowTitle)) {
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			// flag = true;
			return false;
		} finally {
			if (flag) {
				System.out.println("Navigated to the window with title");
			} else {
				System.out.println("The Window with title is not Selected");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean switchToNewWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Window is Navigated with title");
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean switchToOldWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Focus navigated to the window with title");
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}

	/**
	 *
	 */
	@Override
	public int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 *
	 */
	@Override
	public boolean Alert(WebDriver driver) {
		boolean presentFlag = false;
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				System.out.println("The Alert is handled successfully");
			} else {
				System.out.println("There was no alert to handle");
			}
		}

		return presentFlag;
	}

	/**
	 *
	 */
	@Override
	public boolean launchUrl(WebDriver driver, String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Successfully launched \"" + url + "\"");
			} else {
				System.out.println("Failed to launch \"" + url + "\"");
			}
		}
	}

	/**
	 *
	 */
	@Override
	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

	/**
	 *
	 */
	@Override
	public String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			System.out.println("Title of the page is: \"" + text + "\"");
		}
		return text;
	}

	/**
	 *
	 */
	@Override
	public String getCurrentURL(WebDriver driver) {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \"" + text + "\"");
		}
		return text;
	}

	/**
	 *
	 */
	@Override
	public boolean clickOnElement(WebElement element, String locatorName) {
		boolean flag = false;
		try {
			element.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Able to click on \"" + locatorName + "\"");
			} else {
				System.out.println("Click Unable to click on \"" + locatorName + "\"");
			}
		}

	}

	/**
	 * FluentWait
	 * 
	 * @param WebDriver
	 * @param WebElement
	 */
	@Override
	public void fluentWait(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = null;
		int timeout = Integer.parseInt(prop.getProperty("fluentWait"));
		int fluentWaitPooling = Integer.parseInt(prop.getProperty("fluentWaitPooling"));
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(timeout))
					.pollingEvery(Duration.ofSeconds(fluentWaitPooling)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implicit wait
	 * 
	 * @param WebDriver
	 */
	@Override
	public void implicitWait(WebDriver driver) {
		int timeout = Integer.parseInt(prop.getProperty("fluentWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	/**
	 * Explicit wait
	 * 
	 * @param WebDriver
	 * @param WebElement
	 */
	@Override
	public void explicitWait(WebDriver driver, WebElement element) {
		int timeout = Integer.parseInt(prop.getProperty("explicitWait"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * PageLoadTimeout
	 * 
	 * @param WebDrive
	 */
	@Override
	public void pageLoadTimeOut(WebDriver driver) {
		int timeout = Integer.parseInt(prop.getProperty("PageLoadTimeout"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
	}

	/**
	 * Screenshot
	 */
	@Override
	public String screenShot(WebDriver driver, String filename) {
		/*
		 * String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 * TakesScreenshot takesScreenshot = (TakesScreenshot) driver; File source =
		 * takesScreenshot.getScreenshotAs(OutputType.FILE); String destination =
		 * System.getProperty("user.dir") +
		 * "\\ScreenShots\\" + filename + "_" + dateName + ".png";
		 * 
		 * try { FileUtils.copyFile(source, new File(destination)); } catch (Exception
		 * e) { e.getMessage(); } // This new path for jenkins String newImageString =
		 * "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" +
		 * filename + "_" + dateName + ".png"; return newImageString;
		 */
		return null;
	}

	/**
	 * Get Current Date and Time
	 */
	@Override
	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}

}
