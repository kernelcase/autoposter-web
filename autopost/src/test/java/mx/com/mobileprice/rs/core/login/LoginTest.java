package mx.com.mobileprice.rs.core.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.Link;
import com.restfb.types.Photo;
import com.restfb.types.User;

public class LoginTest {

	public static void main(String[] args) throws Exception{
		
		
		String apiKey = "153270308354016";
		String secretKey = "9973784cc72421cf46c05fadffbc3747";
		String shortLivedToken = "CAACLZAgZAItZBABAC8hjouPdFoYudqOyxWzOA4D6aIAvm8mUkHRenMEsRZCKA5ZBpdBB7nHP3lAkAfgQeSg0DV9DPmcbKoCn0WoQfTIKqWcO6W3HaIuZChY7ZAd0YNNfoB6A0ZCwILZA29aq4EmAJYmSAZBr4Fjq47k1RZBPgbZCKg4dvBFMLA3KHGRC5pj7w1sbizIZD";
		
		FacebookClient facebookClient = new DefaultFacebookClient(shortLivedToken, Version.VERSION_2_4);				
		String extendedToken = facebookClient.obtainExtendedAccessToken(apiKey, secretKey).getAccessToken();
		
		System.out.println("ApiKey = " + apiKey);
		System.out.println("SecretKey = " + secretKey);
		System.out.println(shortLivedToken);
		System.out.println(extendedToken);
		
		User user = facebookClient.fetchObject("me", User.class);

		System.out.println("User name: " + user.getName());
		
		File fi = new File("/home/temistocles/mobileprice/cellphones/iphone-6-clon/01.jpg");
		byte[] fileContent = Files.readAllBytes(fi.toPath());
		
		FacebookType photo = facebookClient.publish("me" + "/photos" , FacebookType.class,
	            BinaryAttachment.with("01.jpg", fileContent));
		System.out.println("photo " + photo);
		System.out.println("id "  + photo.getId());
		Photo photoLink = facebookClient.fetchObject(photo.getId(), Photo.class);
		System.out.println("link "  +photoLink);
		FacebookType publishMessageResponse =
				  facebookClient.publish("910678402359806/feed", FacebookType.class,BinaryAttachment.with("01.jpg", fileContent),
				    Parameter.with("message", "Iphone 6 Clon - $2,99"),
				    Parameter.with("picture", "http://www.folfasesores.com/images/folf.png"),
				    Parameter.with("description", "checale bienchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdfchecale bien dafasdfasdf dafasdfasdf")
				  //  Parameter.with("link", "http://www.google.com")
				    );
		String query = 
				"SELECT pid, src, src_small, src_big, caption FROM photo WHERE owner=me";

				List<JsonObject> queryResults = 
				facebookClient.executeFqlQuery(query, JsonObject.class);

				for(int i=0; i<queryResults.size(); i++)
				{
				    String photoUrl = queryResults.get(i).getString("src"); 
				    System.out.println(photoUrl);
				    // add your code to use photoUrl
				}
		/*
         FacebookType publishMessageResponse =
         	  facebookClient.publish("me/feed", FacebookType.class,
         	    Parameter.with("message", "I just joined Sakshum subscribers to make a difference!\n" +
         	    		"Please do join me now to serve the community"));
         		Parameter.with("picture", "https://www.google.com.mx/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
         		Parameter.with("link", "http://www.sakshum.org");
         		Parameter.with("description", "Sakshum is a not for profit organization working in the field of child education and" +
         				" building an effective blood donor pool.");
         */

		System.out.println("Published message ID: " + publishMessageResponse.getId());
	}
	
	
}
