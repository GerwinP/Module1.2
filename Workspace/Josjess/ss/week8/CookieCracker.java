package ss.week8;

import org.apache.commons.codec.binary.Base64;

public class CookieCracker {

	public static void main(String[] args) {
		BadCookieCrypto bcc = new BadCookieCrypto();
		
		String cookie = bcc.createCookie();
		byte[] cookieInBytes = Base64.decodeBase64(cookie);
		
		cookieInBytes[cookieInBytes.length - 1] = (byte) (cookieInBytes[cookieInBytes.length - 1] ^ 'N' ^ 'Y');
		System.out.println(cookie);
		
		System.out.println(bcc.isAdmin(Base64.encodeBase64String(cookieInBytes)));
		
	}

}
