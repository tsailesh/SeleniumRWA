/**
 * @auther sailesh
 *
 */
package RealWorldApp.SeleniumRWA.utility;

/**
 * 
 */
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import RealWorldApp.SeleniumRWA.base.BaseClass;

public class Retry extends BaseClass implements IRetryAnalyzer  {
	int count = 0;
	int maxTry = Integer.parseInt(prop.getProperty("IReTry"));

	@Override
	public boolean retry(ITestResult result) {
		System.out.println("Here is retry");
		if (count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
