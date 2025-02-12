package testCases;

import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.TestBase;

public class TC002_LoginTest extends TestBase {

	@Test(groups= {"Sanity","Master"})
	public void verify_login() {

		logger.info("Starting TC002_LoginTest...");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked my Account");
			
			hp.clickLogin();
			logger.info("Clicked Login");
			Thread.sleep(3000);
			LoginPage lp = new LoginPage(driver);
			lp.setEmailAddress(properties.getProperty("username"));
			logger.info("Email id received..");
			lp.setPassword(properties.getProperty("password"));
			//lp.setPassword("abcd2");//checking for force failing
			logger.info("Password received..");
			Thread.sleep(3000);
			lp.clickLogin();
			logger.info("Clicked Login button with credential..");
			Thread.sleep(3000);
			MyAccountPage macp = new MyAccountPage(driver);
			boolean r = macp.isMyElementExist();
			logger.info("Validating web element- MY ACCOUNT.....");
			assertEquals(r, true);
			logger.info("Validation successful..");
//			macp.clickLogout();
//			logger.info("Clicked Logout...");
		} catch (Exception e) {
			Assert.fail();
			logger.info("Test case failed...");
		}
		
		
		logger.info("***TC002 finished***");
		
	}

}
