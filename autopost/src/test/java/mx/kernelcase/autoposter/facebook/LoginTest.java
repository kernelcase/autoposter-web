package mx.kernelcase.autoposter.facebook;

public class LoginTest {
	public static void main(String args[]) throws Exception {

		Facebook facebook = new Facebook();
		boolean estaEnSesion = facebook.login("tienda.mobileprice@gmail.com", "publico-en0100");
		System.out.println("Esta en sesion: " + estaEnSesion);
	}
}
