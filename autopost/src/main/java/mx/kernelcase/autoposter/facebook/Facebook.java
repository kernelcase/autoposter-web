package mx.kernelcase.autoposter.facebook;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Facebook {

	private WebDriver webDriver;
	
	private boolean estaEnSesion = false;
	
	public Facebook() {

		webDriver = new ChromeDriver();
	}
	
	public boolean login(String usuario, String password) {
		
		Login login = new Login();
		login.setWebDriver(webDriver);
		login.setFacebookUsername(usuario);
		login.setFacebookPassword(password);
		login.run();
		
		estaEnSesion = login.isInSession();
		
		return estaEnSesion;
	}
	
	public List<Grupo> grupos() {
		Grupos grupos = new Grupos();
		 grupos.setWebDriver(webDriver);
		 grupos.run();
		 
		 return grupos.getGroups();
	}
}
