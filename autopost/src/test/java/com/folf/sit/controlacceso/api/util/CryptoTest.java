package com.folf.sit.controlacceso.api.util;

import org.junit.Test;

import com.facebook_autoposter.robot.util.Crypto;

import static org.junit.Assert.assertEquals;

public class CryptoTest {

	@Test
	public void cryptoMessageTest() {
		
		String mensaje = Crypto.hmac("123$root_2015");
		assertEquals("l1qkkG4WvSF+Uv6A588WloJQwfLAkYXfLCN0kcaMbQm5qnA9NYeBFbmh0dz1DMsw/PUUxNph2K7Yjr8Rf+8Bkw==", mensaje);
	}
}
