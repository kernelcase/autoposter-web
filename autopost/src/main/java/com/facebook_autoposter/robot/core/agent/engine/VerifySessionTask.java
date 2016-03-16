package com.facebook_autoposter.robot.core.agent.engine;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class VerifySessionTask {
	
	private WebDriver webDriver;
	
	private boolean isInSession;
	
	public void run() {
		
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriver.get("https://www.facebook.com/groups/?category=membership");
		if(webDriver.getCurrentUrl().contains("login.php")) {
			isInSession = false;
		} else {
			isInSession = true;
		}		
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
