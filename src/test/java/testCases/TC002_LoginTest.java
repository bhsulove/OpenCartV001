package testCases;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass_Test;

public class TC002_LoginTest extends BaseClass_Test {

	@Test(groups={"Sanity","Master"})
	public void user_Login() {

		logger.info("----- Starting TC002_LoginTest -----");
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
			login_page.setLogin_emailAddress(property.getProperty("email"));
			logger.info("Entering login Password");
			login_page.setPassword(property.getProperty("password"));
			logger.info("Clicking on Login Button");
			
			login_page.clickLogin();
			

			// Login
			MyAccountPage my_account = new MyAccountPage(driver);
						
			boolean display_status_myaccount = my_account.isMyAccount_displayed();
			logger.info("Verifying MyAccount is Dispalyed");

			// Assert.assertEquals(display_status_myaccount, true,"---- Login Failed ----");
			Assert.assertTrue(display_status_myaccount,"---- Login Failed ----");
			logger.info("Logging In Successfull. ");
			my_account.clickMyAccount();
			logger.info("--- Logging Off ---");
			my_account.logout();
			
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("----- Closing TC002_LoginTest -----");
	}
}
