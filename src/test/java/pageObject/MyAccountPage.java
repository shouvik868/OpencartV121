package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h2[contains(text(),'My Account')]")
	WebElement elemMyAccount;
	@FindBy(xpath = "//div/a[contains(text(),'Logout')]")
	WebElement buttonLogout;

	public boolean isMyElementExist() {
		
		try {
			
			return elemMyAccount.isDisplayed();
			
		} catch (Exception e) {
			return false;
		}
		

	}
	
	public void clickLogout() {
		buttonLogout.click();
	}

}
