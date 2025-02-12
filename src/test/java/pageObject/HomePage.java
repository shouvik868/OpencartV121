package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	WebElement buttonMyAccount;
	@FindBy(xpath = "//a[contains(text(),'Register')]")
	WebElement buttonRegister;
	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement buttonLogin;

	public void clickMyAccount() {
		buttonMyAccount.click();
	}

	public void clickRegister() {
		buttonRegister.click();
	}
	
	public void clickLogin() {
		buttonLogin.click();
	}
}
