package com.page_objects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.CommonUtils;
import com.webdriver_manager.DriverManager;

public class LoginPage {
	
	private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);
	
	private static LoginPage loginInstance;
	
	private LoginPage(){
	}
	
	public static LoginPage getInstance() {
		if(loginInstance==null) {
			loginInstance = new LoginPage();
		}
		return loginInstance;
	}
	
	/* Using Page Factory
	 * @FindBy(id= "username") private WebElement USERNAME;
	 * 
	 * @FindBy(id= "password") private WebElement PASSWORD;
	 * 
	 * @FindBy(id= "submit") private WebElement LOGIN_BUTTON;
	 */
	
	// Using By Locators
	
	By USERNAME = By.id("username");
	By PASSWORD = By.id("password");
	By LOGIN_BUTTON = By.id("submit");
	
	WebDriver driver = DriverManager.getDriver();

	/*public WebElement getUSERNAME() {
		return USERNAME;
	}

	public WebElement getPASSWORD() {
		return PASSWORD;
	}

	public WebElement getLOGIN_BUTTON() {
		return LOGIN_BUTTON;
	}*/
	
	public void enterUserName(String userName) {
		try {
			CommonUtils.getInstance().highlightElement(driver.findElement(USERNAME));
			//USERNAME.sendKeys(userName);
			driver.findElement(USERNAME).sendKeys(userName);
		} catch (Exception e) {
			System.out.println("Element not found. Trying to find it one more time using explicit wait");
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
			} catch (Exception e2) {
				LOGGER.info(e.getMessage());
			}
		}
		
	}
	
	public void enterPassword(String password) {
		try {
			//CommonUtils.getInstance().highlightElement(PASSWORD);
			CommonUtils.getInstance().highlightElement(driver.findElement(PASSWORD));
			driver.findElement(PASSWORD).sendKeys(password);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		
	}
	
	public void clickLoginButton() {
		try {
//			//CommonUtils.getInstance().highlightElement(LOGIN_BUTTON);
			CommonUtils.getInstance().highlightElement(driver.findElement(LOGIN_BUTTON));
			driver.findElement(LOGIN_BUTTON).click();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}
	
//	By USERNAME = By.name("username");
//	By PASSWORD = By.name("password");
//	By LOGIN_BUTTON = By.xpath("//button[@type='submit']")
			
}
