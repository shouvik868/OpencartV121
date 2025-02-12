package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.TestBase;
import utilities.DataProviders;

public class TC003_Login_DDT extends TestBase {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven")
	public void verify_login__DDT(String username, String password, String expectedResult) {

		logger.info("****Starting TC003_Login_DDT*****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked my Account");

			hp.clickLogin();
			logger.info("Clicked Login");
			Thread.sleep(3000);
			LoginPage lp = new LoginPage(driver);
			lp.setEmailAddress(username);
			logger.info("Email id received..");
			lp.setPassword(password);
			logger.info("Password received..");
			Thread.sleep(3000);
			lp.clickLogin();
			logger.info("Clicked Login button with credential..");
			Thread.sleep(3000);
			MyAccountPage macp = new MyAccountPage(driver);
			boolean target = macp.isMyElementExist();

			/*
			 * Data Valid - login Success - Pass- Logout login Failed - Fail Data invalid -
			 * Login Success - Failed - Logout Login Failed - Passed
			 */

			if (expectedResult.equalsIgnoreCase("Valid")) {
				if (target == true) {
					Assert.assertTrue(true);
					logger.info("My Account page validation successful..");
					macp.clickLogout();
					logger.info("Logging out..");
					logger.info("****test successful***");
				} else {
					Assert.assertTrue(false);
					logger.info("****test failed***");
				}
			}

			if (expectedResult.equalsIgnoreCase("Invalid")) {

				if (target == true) {
					Assert.assertTrue(false);
					logger.info("My Account page validation successful..");
					macp.clickLogout();
					logger.info("Logging out..");
					logger.info("****test failed***");

				} else {
					Assert.assertTrue(true);
					logger.info("****test successful***");
				}
			}

		} catch (Exception e) {
			Assert.fail();
			logger.info("Test case failed...");
		}

		logger.info("***TC003 finished***");
	}

}
