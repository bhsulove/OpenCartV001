package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass_Test;
import utilities.DataProviders;

//Login with valid credentials- login successful - test passed
//Login with valid credentials- login failed - test failed
//Login with invalid credentials- login failed - test passed
//Login with invalid credentials- login passed - test failed
public class TC003_LoginDataDrivenTest extends BaseClass_Test {
	// getting DataProvider class from different package	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class ,groups={"Master","Data Driven"}) 
	public void login_test(String email, String password, String status,String results) {
		logger.info("----- Starting TC003_LoginDataDrivenTest -----");
		try {
			// HomePage
			HomePage home_page = new HomePage(driver);
			home_page.clickMyAccount();
			logger.info("Clicked on My Account. ");
			home_page.clickLogin();
			logger.info("Clicked on Login. ");

			myWait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Returning Customer']")))
					.isDisplayed();
			// Login
			LoginPage login_page = new LoginPage(driver);
			logger.info("Entering Login Email Id");
			login_page.setLogin_emailAddress(email);
			logger.info("Entering login Password");
			login_page.setPassword(password);
			logger.info("Clicking on Login Button");
			login_page.clickLogin();

			// MyAccountPage
			MyAccountPage my_account = new MyAccountPage(driver);			
			boolean display_status_myaccount = my_account.isMyAccount_displayed();
			

			if (status.equalsIgnoreCase("Valid")) {
				if (display_status_myaccount == true) {
					my_account.clickMyAccount();
					logger.info("--- Logging Off ---");
					my_account.logout();
					Assert.assertTrue(true);					
				} else {
					Assert.assertTrue(false);
				}
			}
			if (status.equalsIgnoreCase("Invalid")) {
				if (display_status_myaccount == true) {
					my_account.clickMyAccount();
					//logger.info("--- Logging Off ---");
					my_account.logout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		} finally {
			logger.info("----- Closing TC003_LoginDataDrivenTest -----");
		}
	}
}
