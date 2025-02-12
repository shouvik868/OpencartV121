package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "input-firstname")
	WebElement firstNameText;
	@FindBy(id = "input-lastname")
	WebElement lastNameText;
	@FindBy(id = "input-email")
	WebElement emailText;
	@FindBy(id = "input-telephone")
	WebElement telehoneText;
	@FindBy(id = "input-password")
	WebElement passwordText;
	@FindBy(id = "input-confirm")
	WebElement confirmPasswordText;
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement chkboxPolicy;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement buttonContinue;
	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement msgConfirmation;

	public void setFirstName(String fname) {
		firstNameText.sendKeys(fname);
	}

	public void setLastName(String lname) {
		lastNameText.sendKeys(lname);
	}

	public void setEmail(String email) {
		emailText.sendKeys(email);
	}

	public void setTelephone(String telNo) {
		telehoneText.sendKeys(telNo);
	}

	public void setPassword(String pwd) {
		passwordText.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		confirmPasswordText.sendKeys(pwd);
	}

	public void clickPolicy() {
		chkboxPolicy.click();
	}

	public void clickSubmit() {
		buttonContinue.click();
	}

	public String getConfirmation() {
		try {
			return msgConfirmation.getText();
		} catch (Exception e) {
			// TODO: handle exception
			return (e.getMessage());
		}

	}

}
