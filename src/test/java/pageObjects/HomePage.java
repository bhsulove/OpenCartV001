package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {              //Constructor
		super(driver); 		
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")          //Locators
	WebElement link_MyAccount;
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement link_Register;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement link_Login;

	public void clickMyAccount() {                        //Actions
		link_MyAccount.click();
	}

	public void clickRegister() {
		link_Register.click();
	}
	public void clickLogin() {
		link_Login.click();
	}
}
