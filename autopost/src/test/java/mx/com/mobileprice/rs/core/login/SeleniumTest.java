package mx.com.mobileprice.rs.core.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


class FacebookPoster {
	
	public void post() {
		
		postGenerales2();
	}
	
	private void postGenerales2(){
		
		// Parametros de configuracion
		String foto = "/home/temistocles/mobileprice/cellphones/iphone-6-clon/01.jpg";
		
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys("tienda.mobileprice@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("publico-en0100");
		driver.findElement(By.id("loginbutton")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		List<String> grupos = gruposGenerales();
		
		for(String grupo: grupos) {		
		
			driver.navigate().to(grupo);
			
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch(Exception ex) {
				System.out.println("No hay alert");
			}
			
			String msg = "";
			msg += "Iphone 6 Clon - $3,200\n\n";
			msg += "   - Gama MEDIA \n";
			msg += "   - Cuadcore MTK 1.3 Mhz \n";			
			msg += "   - 1 GB en RAM \n";
			msg += "   - 16 GB en ROM \n";
			msg += "   - Cárama Trasera de 8 MP y Frontal de 3 MP \n\n";
			msg += "   - Pantalla 1280 x 720 Full HD \n";
			msg += "   - Liberado para cualquier compañia \n";
			msg += "Informes: (981) 126 6061 \n";
				
			
			WebElement we = driver.findElement(By.tagName("textarea"));
			we.click();
			we.sendKeys(msg);	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			// Buscar el elemento donde se cargan las fotos			
			WebElement cargarFoto = driver.findElement(By.cssSelector("input[name='composer_photo']"));
			cargarFoto.sendKeys(foto);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			WebElement botonEnvio = driver.findElement(By.xpath("//button[@data-testid='react-composer-post-button' and not(@disabled)]"));			
		//	botonEnvio.click();
			
		}
		
		
	}
	
	private void postGenerales() {
		String linkArticulo = "https://www.facebook.com/photo.php?fbid=1505521796436861";
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys("tienda.mobileprice@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("publico-en0100");
		driver.findElement(By.id("loginbutton")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		List<String> grupos = gruposGenerales2();
		
		for(String grupo: grupos) {		
		
			driver.navigate().to(grupo);
			
			String msg = "";
			msg += "Iphone 5 $2,800\n";			
			msg += "   - 16 GB \n";
			msg += "   - Liberado \n";
			msg += "   - Estética 8.5 \n\n";			
			msg += linkArticulo;
			
			WebElement we = driver.findElement(By.tagName("textarea"));
			we.click();
			we.sendKeys(msg);		
			 
			WebElement buttons= driver.findElement(By.cssSelector("button[data-testid='react-composer-post-button']"));
			 		 
			// Pequena pausa
			List<WebElement> imagenes = driver.findElements(By.xpath("//span[contains(text(),'" + linkArticulo +"')]"));
			imagenes.get(0).click();
			
			
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			 // Enviar el post
		//	buttons.click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	private List<String> gruposGenerales2() {
		List<String> grupos = new ArrayList<String>();
		grupos.add(gruposGenerales().get(0));
		return grupos;
	}
	
	private List<String> gruposGenerales() {
		String[] gruposArray = {
				"https://www.facebook.com/groups/526780147338922",
			//	"https://www.facebook.com/groups/260991247250036",
				"https://www.facebook.com/groups/603474619682958",
			//	"https://www.facebook.com/groups/1471141879765746",
				"https://www.facebook.com/groups/334765633339461",
			//	"https://www.facebook.com/groups/244041192440160",
			//	"https://www.facebook.com/groups/455287924529734",
			//	"https://www.facebook.com/groups/317048615075217",
			//	"https://www.facebook.com/groups/168397783258112",
			//	"https://www.facebook.com/groups/532990666814346",
				"https://www.facebook.com/groups/1644567519163038"
		};
		
		List<String> gruposGenerales = Arrays.asList(gruposArray);
		
		return gruposGenerales;
		
	}
}

public class SeleniumTest {

	public static void main(String args[]) throws Exception {
		
		FacebookPoster facebookPoster = new FacebookPoster();
		facebookPoster.post();
	}
}
