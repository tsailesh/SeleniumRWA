/**
 * @auther sailesh
 *
 */
package RealWorldApp.SeleniumRWA.tests;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import RealWorldApp.SeleniumRWA.base.BaseClass;
import RealWorldApp.SeleniumRWA.utility.DataReader;
import RealWorldApp.SeleniumRWA.utility.Retry;


public class LoginTest extends BaseClass{
	
	@Test(priority = 1,dataProvider = "loginDataFromJSON")
	public void login(HashMap<String, String> datas) throws InterruptedException {
		if (datas.get("validation").equals("empty")) {
			System.out.println("When Cred's Are Empty");
			Boolean enabled = login.isLoginBtnEnabled();
			Assert.assertFalse(enabled, "Button is enabled");
		} else if (datas.get("validation").equals("failure")) {
			System.out.println("When Cred's Are Invalid");
			login.loginToPage(datas.get("username"), datas.get("password"));
			String validationMessage = login.getValidationMessage().trim();
			Assert.assertEquals(validationMessage, prop.getProperty("loginValidationMessage"), "Message did not matched");
		} else if(datas.get("validation").equals("success")){
			System.out.println("When Cred's Are Valid");
			login.loginToPage(datas.get("username"), datas.get("password"));
			Assert.assertNotEquals(driver.getCurrentUrl(), prop.getProperty("baseUrl"),
					"URL BEFORE LOGGED IN AND AFTER LOGGED IN MATCHED");
		}
	}
	/*
	@Test(priority = 2,retryAnalyzer = Retry.class)
	public void exmaple_of_iRetryAnalyzer() {
		login.loginToPage("ram", "rambdr");
		Assert.assertNotEquals(driver.getCurrentUrl(), prop.getProperty("baseUrl"),
				"URL BEFORE LOGGED IN AND AFTER LOGGED IN MATCHED");
	}
	*/
	@DataProvider
	public Object[][] loginDataFromJSON() {
		List<HashMap<String, String>> dataFromJSONFile = DataReader.getJSONdataToMap(
				System.getProperty("user.dir") + prop.getProperty("pathToLoginJSON"));
		Object[][] dataArray = new Object[dataFromJSONFile.size()][1];

		for (int i = 0; i < dataFromJSONFile.size(); i++) {
			dataArray[i][0] = dataFromJSONFile.get(i);
		}
		return dataArray;
	}
}
