/**
 * @auther sailesh
 *
 */
package RealWorldApp.SeleniumRWA.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RealWorldApp.SeleniumRWA.actions.Action;

/**
 * 
 */
public class HomePage extends Action{

	HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[data-test='sidenav-username']")
	private WebElement username_sidenav;
	
	public void getUserNameAfterLoggedIn(String username) {
		selectByVisibleText(username, username_sidenav);
	}
	
}
