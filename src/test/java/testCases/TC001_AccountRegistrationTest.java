package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass_Test;

public class TC001_AccountRegistrationTest extends BaseClass_Test {

	@Test(groups={"Regression","Master"})
	public void account_registration() {
		logger.info("------- Staring TC001_AccountRegistationTest -------");

		try {
			HomePage home_page = new HomePage(driver);
			home_page.clickMyAccount();
			logger.info("Clicked on MyAccount ");
			home_page.clickRegister();
			logger.info("Clicked on Register");			

			AccountRegistrationPage account_register = new AccountRegistrationPage(driver);
			logger.info("Filling up Registration Form. ");
			account_register.setFirstName(randomString().toUpperCase()); // randomly generated name
			// account_register.setFirstName("Naan");
			account_register.setLastName(randomString().toUpperCase());
			account_register.setEmail(randomString().toLowerCase() + "@gmail.com"); // radomly generated email
			account_register.setTelephone(randomNumber());

			String random_pwd = randomAlphaNumeric(); // Creating random password
			account_register.setPassword(random_pwd);
			account_register.setConfirmPassword(random_pwd);

			account_register.setNewsSubscription();
			account_register.setPrivacyPolicy();
			account_register.clickContinue();

			logger.info("Validation of confirmation message. ");
			String confirmation_message = account_register.getConfirmationMsg();
			if (!confirmation_message.equalsIgnoreCase("Your Account Has Been Created!")) {
				logger.error("Account Registration Failed. ");
				// logger.debug("---- Debug Logs ---- "); //Change <Root level="Debug"> inside <Loggers> - log4j2.xml
			}
			Assert.assertEquals(confirmation_message, "Your Account Has Been Created!");
			logger.info("Message Validation Successfull.");

		} catch (Exception e) {
			Assert.fail();
			// e.printStackTrace();
		}
		logger.info("------- Closing TC001_AccountRegistationTest -------");
	}

}
