package testCases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.internal.Debug;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.TestBase;

public class TC001_AccountRegistrationTest extends TestBase{
	
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("*****Startting TC001****");
		try {
			
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked my Account");
		hp.clickRegister();
		logger.info("Clicked Register");
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		arp.setFirstName("abcd");
		logger.info("First name set");
		System.out.println("fname ok");
		arp.setLastName("yuhj");
		logger.info("Last name set");
		System.out.println("lname ok");
		String randomEmailname = randomString();
		arp.setEmail(randomEmailname+"@hjo.com");
		logger.info("Email set");
		System.out.println("email ok with "+ randomEmailname+"@hjo.com");
		Thread.sleep(3000);
		arp.setTelephone("127864534");
		logger.info("Telephone no set");
		System.out.println("tele ok");
		arp.setPassword("asd123");
		logger.info("Password set");
		System.out.println("pass ok");
		arp.setConfirmPassword("asd123");
		logger.info("Confirm Password set");
		System.out.println("repass ok");
		arp.clickPolicy();
		logger.info("Policy checked");
		System.out.println("policy ok");
		arp.clickSubmit();
		logger.info("Submit Clicked");
		System.out.println("submit ok");
		Thread.sleep(5000);
		logger.info("Validation Confirming..");
		String confmsg=arp.getConfirmation();
		assertEquals(confmsg, "Your Account Has Been Created!");
		logger.info("Assertion Successful");
		
		}catch (Exception e) {
			logger.error("Test failed");
			logger.debug("log Debug..");
			Assert.fail();
		}
		
		logger.info("***TC001 finished***");
	}
	
	
}
