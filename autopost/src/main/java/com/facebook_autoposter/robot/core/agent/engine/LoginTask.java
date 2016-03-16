package com.facebook_autoposter.robot.core.agent.engine;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTask {

	private String facebookUsername;
	
	private String facebookPassword;
	
	private WebDriver webDriver;
	
	private boolean isInSession;
	
	public void run() {
		
		Validate.notNull(webDriver, "WebDriver is null");
		Validate.notNull("facebookUsername",facebookUsername);
		Validate.notNull("facebookPassword",facebookPassword);
				
		webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		webDriver.get("https://www.facebook.com");
		webDriver.findElement(By.id("email")).sendKeys(facebookUsername);
		webDriver.findElement(By.id("pass")).sendKeys(facebookPassword);
		webDriver.findElement(By.id("loginbutton")).click();
		
		
		
		
		try {
			WebElement welcomeLink = webDriver.findElement(By.className("fbxWelcomeBoxName"));
			if(welcomeLink != null) {
				this.isInSession = true;
			}
		} catch(Exception ex) {
			this.isInSession = false;
		}
		
		
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	public String getFacebookPassword() {
		return facebookPassword;
	}

	public void setFacebookPassword(String facebookPassword) {
		this.facebookPassword = facebookPassword;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public boolean isInSession() {
		return isInSession;
	}

	public void setInSession(boolean isInSession) {
		this.isInSession = isInSession;
	}

	
}
