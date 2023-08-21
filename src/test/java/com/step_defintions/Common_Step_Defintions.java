package com.step_defintions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.utilities.CommonUtils;
import com.webdriver_manager.DriverManager;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Common_Step_Defintions {
	
	private static String scenarioName =null;

	public static String getScenarioName() {
		return scenarioName;
	}

	public static WebDriver driver=null;
	
	private static final Logger LOGGER = LogManager.getLogger(Common_Step_Defintions.class);
	
	@Before
	public void beforeScenario(Scenario scenario) {
		try {
			
		scenarioName = scenario.getName();
			
		LOGGER.info("Execution Started");
		
		
		LOGGER.info("Loading the Properties File");
		CommonUtils.getInstance().loadProperties();
		
		LOGGER.info("Checking the driver is null or not");
		if(driver==null) {
			
			LOGGER.info("Driver is NULL. Instantiating It");
			DriverManager.launchBrowser();
			DriverManager.getDriver().manage().window().maximize();
			
			//CommonUtils.getInstance().initWebElements();
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterStep
	public void attachScreenshot(Scenario scenario) {
		if(scenario.isFailed()) {
			byte[] screenshotTaken = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshotTaken, "image/png", "Error Screen");
		}
	}
	
}
