package com.step_defintions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.constants.Constants;
import com.page_objects.HomePage;
import com.page_objects.LoginPage;
import com.utilities.CommonUtils;
import com.webdriver_manager.DriverManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TestLoginSteps {
	
	private static final Logger LOGGER = LogManager.getLogger(TestLoginSteps.class);

	@Given("User provides the {string} and {string}")
	public void user_provides_the_and(String userName, String password) {
		try {
			DriverManager.getDriver().get(Constants.APP_URL);
			LoginPage.getInstance().enterUserName(Constants.UserName);
			LoginPage.getInstance().enterPassword(Constants.Password);
			LoginPage.getInstance().clickLoginButton();
			LOGGER.info("Username, Password entered successfully");
		} catch (Exception e) {
			CommonUtils.getInstance().takeScrenshot();
			LOGGER.error(e);
			Assert.fail(e.getMessage());
		}
	}

	@Given("User verifies the successful login to landing page")
	public void user_verifies_the_successful_login_to_landing_page() {
		try {
		String actHeaderTitle = HomePage.getInstance().getHeaderTitle();
		if(actHeaderTitle.contains("Sucessfully")) {
			LOGGER.info("Logged in Successfully");
		}
		}catch(Exception e) {
			CommonUtils.getInstance().takeScrenshot();
			LOGGER.error(e);
			Assert.fail(e.getMessage());
		}
	}

	@Then("User clicks on logout and verifies the login page")
	public void user_clicks_on_logout_and_verifies_the_login_page() throws InterruptedException {
		try {
		HomePage.getInstance().clickLogoutButton();
		Thread.sleep(5000);
		DriverManager.getDriver().getCurrentUrl().contains("login");
		}catch(Exception e) {
			CommonUtils.getInstance().takeScrenshot();
			LOGGER.error(e);
			Assert.fail(e.getMessage());
		}
	}

}
