package RealWorldApp.SeleniumRWA.actionInterface;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {
	
	public void scrollByVisibilityOfElement(WebDriver driver, WebElement element);
	public void click(WebDriver ldriver, WebElement element);
	public boolean isDisplayed(WebElement element);
	public boolean type(WebElement ele, String text,Keys keys);
	public boolean findElement(WebDriver ldriver, WebElement element);
	public boolean isSelected(WebDriver ldriver, WebElement element);
	public boolean isEnabled(WebDriver ldriver, WebElement element);
	public boolean selectBySendkeys(String value,WebElement element);
	public boolean selectByIndex(WebElement element, int index);
	public boolean selectByValue(WebElement element,String value);
	public boolean selectByVisibleText(String visibletext, WebElement element);
	public boolean mouseHoverByJavaScript(WebElement locator);
	public boolean JSClick(WebDriver driver, WebElement element);
	public boolean switchToFrameByIndex(WebDriver driver,int index);
	public boolean switchToFrameById(WebDriver driver,String idValue);
	public boolean switchToFrameByName(WebDriver driver,String nameValue);
	public boolean switchToDefaultFrame(WebDriver driver);
	public void mouseOverElement(WebDriver driver,WebElement element);
	public boolean moveToElement(WebDriver driver, WebElement element);
	public boolean mouseover(WebDriver driver, WebElement element);
	public boolean draggable(WebDriver driver,WebElement source, int x, int y);
	public boolean draganddrop(WebDriver driver,WebElement source, WebElement target);
	public boolean slider(WebDriver driver,WebElement element, int x, int y);
	public boolean rightclick(WebDriver driver,WebElement element);
	public boolean switchWindowByTitle(WebDriver driver,String windowTitle, int count);
	public boolean switchToNewWindow(WebDriver driver);
	public boolean switchToOldWindow(WebDriver driver);
	public int getColumncount(WebElement row);
	public int getRowCount(WebElement table);
	public boolean Alert(WebDriver driver);
	public boolean launchUrl(WebDriver driver,String url);
	public boolean isAlertPresent(WebDriver driver);
	public String getCurrentURL(WebDriver driver);
	public String getTitle(WebDriver driver);
	public boolean clickOnElement(WebElement locator, String locatorName);
	public void fluentWait(WebDriver driver,WebElement element);
	public void implicitWait(WebDriver driver);
	public void explicitWait(WebDriver driver, WebElement element);
	public void pageLoadTimeOut(WebDriver driver);
	public String screenShot(WebDriver driver, String filename);
	public String getCurrentTime();

}
