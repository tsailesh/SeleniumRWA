package RealWorldApp.SeleniumRWA.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RealWorldApp.SeleniumRWA.actions.Action;

public class LoginPage extends Action {
	LoginPage login;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Objects

	@FindBy(id = "username")
	private static WebElement useremail;

	@FindBy(id = "password")
	private static WebElement userpassword;

	@FindBy(css = "button[type='submit']")
	private static WebElement loginButton;

	@FindBy(className = "MuiAlert-message")
	private static WebElement alertMessage;

	@FindBy(id = "username-helper-text")
	private static WebElement usernameValidationMessage;

	public void typeUserName(String username) {
		try {
			useremail.sendKeys(username);
		} catch (Exception e) {
			System.out.println("Username is null");
			e.printStackTrace();
		}
	}

	public void typePassword(String password) {
		try {
			userpassword.sendKeys(password);
		} catch (Exception e) {
			System.out.println("Username is null");
			e.printStackTrace();
		}
	}

	public void clickLogin() {
		try {
			loginButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	// Actions
	public Boolean isLoginBtnEnabled() {
		type(useremail, null, Keys.TAB);
		boolean enabled = isEnabled(driver, alertMessage) ? true : false;
		return enabled;
	}

	public HomePage loginToPage(String username, String password) {
		typeUserName(username);
		typePassword(password);
		clickLogin();
		return new HomePage(driver);
	}

	public String getValidationMessage() {
		String message = getText(alertMessage);
		return message;
	}

}
