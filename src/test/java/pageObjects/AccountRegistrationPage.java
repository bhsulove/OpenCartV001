package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		//passing driver to the parent class(BasePage) constructor
		super(driver);		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")	WebElement input_first_name;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement input_last_name;
	@FindBy(xpath="//input[@id='input-email']") WebElement input_email;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement input_telephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement input_password;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement password_confirm;
	@FindBy(xpath="//label[normalize-space()='Yes']") WebElement newsSub_checknox;
	@FindBy(xpath="//input[@name='agree']") WebElement privacy_policy;
	@FindBy(xpath="//input[@value='Continue']") WebElement button_continue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msg_confirmation;
	
	public void setFirstName(String first_name) {
		input_first_name.sendKeys(first_name);
	}
	public void setLastName(String last_name) {
		input_last_name.sendKeys(last_name);
	}
	public void setEmail(String email) {
		input_email.sendKeys(email);
	}
	public void setTelephone(String telephone) {
		input_telephone.sendKeys(telephone);
	}
	public void setPassword(String password) {
		input_password.sendKeys(password);
	}
	public void setConfirmPassword(String password) {
		password_confirm.sendKeys(password);
	}
	public void setNewsSubscription() {
		newsSub_checknox.click();
		//newsSub_checknox.submit();
	}
	public void setPrivacyPolicy() {
		privacy_policy.click();
	}
	public void clickContinue() {
		button_continue.click();
	}
	public String getConfirmationMsg() {
		try {
			return(msg_confirmation.getText());
		}catch (Exception e) {
			return (e.getMessage());
		}
	}
}
	


