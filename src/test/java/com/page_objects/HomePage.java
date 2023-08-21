package com.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webdriver_manager.DriverManager;

public class HomePage {
	
	private static HomePage homePageInstance;
	
	private HomePage() {
		
	}
	
	public static HomePage getInstance() {
		if(homePageInstance==null) {
			homePageInstance = new HomePage();
		}
		return homePageInstance;
	}
	
	By headerTitle = By.tagName("h1");
	By linkText = By.linkText("Log out");
	
	WebDriver driver = DriverManager.getDriver();
	
	/*
	 * @FindBy(tagName = "h1") private WebElement headerTitle;
	 * 
	 * @FindBy(linkText = "Log out") private WebElement LogoutButton;
	 */

	/*public WebElement getDIRECTORY() {
		return DIRECTORY;
	}*/
	
	public String getHeaderTitle() {
		return driver.findElement(headerTitle).getText();
	}
	
	public void clickLogoutButton() {
		driver.findElement(linkText).click();
	}
	
}
