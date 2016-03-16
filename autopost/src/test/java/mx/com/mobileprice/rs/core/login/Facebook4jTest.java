package mx.com.mobileprice.rs.core.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Group;
import facebook4j.Media;
import facebook4j.PhotoUpdate;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;

public class Facebook4jTest {

	public static void main(String args[]) throws Exception {
		
		String appId = "153270308354016";
		String appSecret = "9973784cc72421cf46c05fadffbc3747";
		String accessToken = "CAACLZAgZAItZBABAC8hjouPdFoYudqOyxWzOA4D6aIAvm8mUkHRenMEsRZCKA5ZBpdBB7nHP3lAkAfgQeSg0DV9DPmcbKoCn0WoQfTIKqWcO6W3HaIuZChY7ZAd0YNNfoB6A0ZCwILZA29aq4EmAJYmSAZBr4Fjq47k1RZBPgbZCKg4dvBFMLA3KHGRC5pj7w1sbizIZD";
		
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthAccessToken(new AccessToken(accessToken));

		/*
		InputStream in = new FileInputStream(new File("/home/temistocles/mobileprice/cellphones/iphone-5/fb.jpg"));
		
		Media media = new Media("fb.jpg", in);
		PhotoUpdate photoUpdate = new PhotoUpdate(media);
		String postId = facebook.postPhoto(photoUpdate);
	System.out.println(postId);
	*/
	//
	
	/*
		List<Group> groups =  facebook.searchGroups("campeche");
		for(Group g: groups) {
			System.out.println("Group " + g.getId() +  " " + g.getDescription());
		}
		*/
		
/*
		String postId = "1505352799787094";
		URL urlPhoto = facebook.getPictureURL(postId);
		System.out.println("URL " + urlPhoto);
		System.out.println("PostID " + postId);
*/		
	
		String[] gruposArray = {
				"526780147338922",
				"260991247250036",
				"603474619682958",
				"1471141879765746",
				"334765633339461",
				"244041192440160",
				"455287924529734",
				"317048615075217",
				"1505189803058506",
				"168397783258112",
				"532990666814346",
				"1644567519163038"
		};
		
		
		int correctos = 0;
		for(int i=0; i < gruposArray.length; i++) {
			try {
				String groupId = gruposArray[i];
				
				URL urlPhoto = facebook.getPictureURL("1505521796436861");
				PostUpdate post = new PostUpdate(new URL("https://tiendamobilecloneblog.wordpress.com/2015/10/04/iphone-5/"))
				.message("Iphone 5, estética 8.5, liberado. ")
		        .picture(urlPhoto)
		        .name("Iphone 5 - $2,800")
		        .description("Zona Centro, Tlaxcala. Informes: (981) 126 6061.");
				String id = facebook.postGroupFeed(groupId,post);
				System.out.println(id);				
				correctos++;
			} catch(FacebookException ex) {
				
			}
		}
		System.out.println("enviados " + correctos + " de " + gruposArray.length);
		//String postId = "1505512613104446";
		
		
		/*
		
		int correctos = 0;
		for(int i=0; i < gruposArray.length; i++) {
			try {
				String groupId = gruposArray[i];
				
				String postId = "1505512613104446";
				URL urlPhoto = facebook.getPictureURL(postId);
				PostUpdate post = new PostUpdate(new URL("https://tiendamobilecloneblog.wordpress.com/2015/10/04/iphone-5/"))
				.message("¡Compralo ahora en Tienda Mobile! Aceptamos cambios.")
		        .picture(urlPhoto)
		        .name("Iphone 5 $2,900")
		        .description("Entregamos en Zona Centro, San Francisco de Campeche.");
				String id = facebook.postGroupFeed(groupId,post);
				System.out.println(id);
				correctos++;
			} catch(FacebookException ex) {
				
			}
		}
		
		/*
		int correctos = 0;
		for(int i=0; i < gruposArray.length; i++) {
			try {
				String groupId = gruposArray[i];
				
				String postId = "1505431643112543";
				URL urlPhoto = facebook.getPictureURL(postId);
				PostUpdate post = new PostUpdate(new URL("https://tiendamobilecloneblog.wordpress.com/2015/10/03/iphone-6-clone/"))
				.message("¡Compralo ahora en Tienda Mobile! Aceptamos cambios.")
		        .picture(urlPhoto)
		        .name("Iphone 6 Clone $2,800")
		        .description("Entregamos en Zona Centro, San Francisco de Campeche.");
				String id = facebook.postGroupFeed(groupId,post);
				System.out.println(id);
				correctos++;
			} catch(FacebookException ex) {
				
			}
		}
		
		System.out.println("enviados " + correctos + " de " + gruposArray.length);
	/*
		// 1505352799787094
		PostUpdate post = new PostUpdate(new URL("https://tiendamobilecloneblog.wordpress.com/2015/10/03/samsung-galaxy-s6-clon/"))
		.message("¡Compralo ahora en Tienda Mobile! Aceptamos cambios.")
        .picture(urlPhoto)
        .name("Samsung Galaxy S6 Clone - Gama Media $3,400")
        .description("Entregamos en Zona Centro, San Francisco de Campeche.");
		String id = facebook.postGroupFeed("1644567519163038",post);
		System.out.println(id);
*/
	}
}
