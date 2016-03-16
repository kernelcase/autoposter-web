package com.facebook_autoposter.robot.core.agent.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserGroupsTask {

	private String facebookUsername;
	
	private WebDriver webDriver;
	
	private List<Group> groups;
	
	public void run() {
		groups = new ArrayList<Group>();
		
		
		webDriver.get("https://www.facebook.com/groups/?category=membership");
		String goToEnd = Keys.chord(Keys.CONTROL, Keys.END);
		List<WebElement> links = webDriver.findElements(By.className("groupsRecommendedTitle"));
		int last = 0;
		int size = links.size();
		
		while(last < size) {
			links.get(0).sendKeys(goToEnd);
			links = webDriver.findElements(By.className("groupsRecommendedTitle"));
			last =  size;
			size = links.size();
		}
		
		for(int i = 0; i < links.size(); i++) {
			WebElement link = links.get(i);
			String ref = link.getAttribute("data-hovercard");
			String idGroup = StringUtils.remove(ref, "/ajax/hovercard/group.php?id=");			
			String groupName = link.getText();
			String groupUrl = "https://www.facebook.com/groups/" + idGroup +"/?ref=browser";
			Group group = new Group();
			group.setIdGroup(idGroup);
			group.setGroupName(groupName);
			group.setGroupUrl(groupUrl);
			groups.add(group);
		}
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	
	
	
}
