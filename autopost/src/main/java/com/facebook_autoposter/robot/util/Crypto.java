package com.facebook_autoposter.robot.util;


import java.security.Key;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypto {

	public static String hmac(String mensaje) {
		
		try {
			Mac magic = Mac.getInstance("HmacSHA512");
	
			String sal = "el universo me dio gratis, yo doy gratis";
			byte[] salt = sal.getBytes("UTF-8");
			byte[] valueBytes = mensaje.getBytes("UTF-8");
			Key key = new SecretKeySpec(salt, "HmacSHA512");
			magic.init(key);
			return Base64.encodeBase64String(magic.doFinal(valueBytes));
//			byte[] retVal = magic.doFinal(valueBytes);
//			for (int i = 1; i < 100; i++) {
//				retVal = magic.doFinal(retVal);
//			}
//			sal = new String(retVal);
//			if (sal.length() > 65535) {
//				sal = sal.substring(0, 65535);
//			}
//			return sal;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}
}

