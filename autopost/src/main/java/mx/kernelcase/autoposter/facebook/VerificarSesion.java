package mx.kernelcase.autoposter.facebook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class VerificarSesion {
	
	private WebDriver webDriver;
	
	private boolean estaEnSesion;
	
	public void run() {
		
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriver.get("https://www.facebook.com/groups/?category=membership");
		if(webDriver.getCurrentUrl().contains("login.php")) {
			estaEnSesion = false;
		} else {
			estaEnSesion = true;
		}		
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public boolean isInSession() {
		return estaEnSesion;
	}

	public void setInSession(boolean isInSession) {
		this.estaEnSesion = isInSession;
	}
	
	
}
