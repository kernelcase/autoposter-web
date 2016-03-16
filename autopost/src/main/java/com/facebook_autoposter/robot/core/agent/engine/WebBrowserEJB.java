package com.facebook_autoposter.robot.core.agent.engine;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Singleton
public class WebBrowserEJB {
	
	private Map<Integer, WebDriver> webDriverMap = new HashMap<Integer,WebDriver>();
	
	
	@Lock(LockType.WRITE)
	public WebDriver get(Integer idFacebook) {						
		
		// Gets the WebDriver from the map
		WebDriver webDriver = webDriverMap.get(idFacebook);
		
		// If doesn't exists create web driver
		if(webDriver == null) {
			webDriver = new ChromeDriver();
			webDriverMap.put(idFacebook, webDriver);
		}
		
		return webDriver;		
	}
	
	@Lock(LockType.WRITE)
	public WebDriver remove(Integer idFacebook) {						
		
		// Gets the WebDriver from the map
		WebDriver webDriver = webDriverMap.remove(idFacebook);				
		return webDriver;		
	}
	
}
