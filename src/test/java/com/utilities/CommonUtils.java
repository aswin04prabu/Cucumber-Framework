package com.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.constants.Constants;
import com.page_objects.HomePage;
import com.page_objects.LoginPage;
import com.step_defintions.Common_Step_Defintions;
import com.webdriver_manager.DriverManager;

public class CommonUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(CommonUtils.class);
	
	private static CommonUtils commonUtilsInstance = null;
	
	public CommonUtils() {
	}

	public static CommonUtils getInstance() {
		if(commonUtilsInstance==null) {
			commonUtilsInstance = new CommonUtils();
		}
		return commonUtilsInstance;
	}
	
	public void loadProperties(){
		
		Properties properties = new Properties();
		
		try {
			properties.load(getClass().getResourceAsStream("/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Constants.APP_URL = properties.getProperty("APP_URL");
		Constants.BROWSER = properties.getProperty("BROWSER");
		Constants.UserName = properties.getProperty("UserName");
		Constants.Password = properties.getProperty("Password");
	}
	
	/*
	 * public void initWebElements() {
	 * PageFactory.initElements(DriverManager.getDriver(), LoginPage.getInstance());
	 * PageFactory.initElements(DriverManager.getDriver(), HomePage.getInstance());
	 * }
	 */
	
	public void takeScrenshot() {
		File screenShot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		
		//Copy the file to a location and use try catch to handle exception
		try {
			FileUtils.copyFile(screenShot, new File("src/test/resources/Screenshots/"+Common_Step_Defintions.getScenarioName()+".png"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void highlightElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
		executor.executeScript("arguments[0].setAttribute('style','border: 2px solid blue')", element);
	}
	
	public void selectFromDropDown(WebElement dropDown, String howTo, String value) {
		
		Select select = new Select(dropDown);
		
		switch(howTo) {
		case "index":
			select.selectByIndex(Integer.parseInt(value));
			break;
		case "value":
			select.selectByValue(value);
			break;
		case "text":
			select.selectByVisibleText(value);
			break;
		default:
			LOGGER.info("Please provide a valid selection. Valid Selections are: text, value, index");
			break;
		}
	}
	
}
