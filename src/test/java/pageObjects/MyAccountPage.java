package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
	WebElement conf_message;
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement dropdown_MyAccount;
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement myAccount_dropdown_logout;
	
	

	public boolean isMyAccount_displayed() {
		try {			
			return conf_message.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * public void findConf_message() { WebDriverWait myWait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20));
	 * myWait.until(ExpectedConditions.visibilityOfElementLocated(By.
	 * xpath("//h2[text()='My Account']"))); }
	 */
	public void clickMyAccount() {
		dropdown_MyAccount.click();
		
	}

	public void logout() {
		myAccount_dropdown_logout.click();
	}
}
