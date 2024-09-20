package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement login_email_Address;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement login_password;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement login_button;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logout_button;

	public void setLogin_emailAddress(String userName) {
		login_email_Address.sendKeys(userName);
	}

	public void setPassword(String password) {
		login_password.sendKeys(password);
	}

	public void clickLogin() {
		login_button.click();
	}

	
	public void clickLogout() {
		login_button.click();
	}
}
