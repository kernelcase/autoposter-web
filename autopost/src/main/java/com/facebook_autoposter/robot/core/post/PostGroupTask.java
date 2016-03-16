package com.facebook_autoposter.robot.core.post;

import org.openqa.selenium.WebDriver;

public class PostGroupTask {

	private String groupUrl;
	
	private String photoPath;
	
	private String message;	
	
	private WebDriver webDriver;
	
	public void run() {
		
	}

	public String getGroupUrl() {
		return groupUrl;
	}

	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	
	
}
