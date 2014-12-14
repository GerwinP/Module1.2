package ss.week5;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.Base64;

/**
 * A simple class that experiments with the Hex encoding
 * of the Apache Commons Codec library.
 *
 */

public class EncodingTest {
	
	private static final String hello =  "Hello World";
	private static final String a1 = "a";
	private static final String a2 = "aa";
	private static final String a3 = "aaa";
	private static final String a4 = "aaaa";
	private static final String a5 = "aaaaa";
	private static final String a6 = "aaaaaa";
	private static final String a7 = "aaaaaaa";
	private static final String a8 = "aaaaaaaa";
	private static final String a9 = "aaaaaaaaa";
	private static final String a10 = "aaaaaaaaaa";
	
	private static String encodeHex(String input){
		String hex = Hex.encodeHexString(input.getBytes());
		return hex;
	}
	
	private static String decodeHex(String input){
		char[] hexArray = input.toCharArray();
		byte[] decode = null;
		try {
			decode = Hex.decodeHex(hexArray);
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String decoded = new String(decode);
		return decoded;
	}
	
	private static String encodeBase(String input){
		String base64 = Base64.encodeBase64String(input.getBytes());
		return base64;
	}
	
	private static String encodeBase(byte[] input){
		String base64 = new String(Base64.encodeBase64(input));
		return base64;
	}
	
	private static String decodeBase(String input){
		byte[] base64 = Base64.decodeBase64(input);
		String base64String = new String(base64);
		return base64String;
	}
	
	public static void main(String[] args) throws DecoderException {
		//Begin of decoding with Hex
		System.out.println("	Beginning of the Hex part");
		System.out.println(encodeHex(hello));
		System.out.println(decodeHex(encodeHex(hello)));
		System.out.println("	End of the Hex part");
		//End of decoding with Hex
		
		//Begin of decoding with Base64
		System.out.println("	Beginning of the Base64 part");
		String base64 = encodeBase(hello);
		System.out.println(base64);
		String hexInput = "010203040506";
		byte[] hexInputdecoded = decodeHex(hexInput).getBytes();
		System.out.println(hexInputdecoded);
		String hexInputEncodedBase = encodeBase(hexInputdecoded);
		System.out.println(hexInputEncodedBase);
		System.out.println("	End of the Base64 part");
		System.out.println("");
		System.out.println("	Begin of the aaaaaaaaaaaaaaa");
		System.out.println(encodeBase(a1));
		System.out.println(encodeBase(a2));
		System.out.println(encodeBase(a3));
		System.out.println(encodeBase(a4));
		System.out.println(encodeBase(a5));
		System.out.println(encodeBase(a6));
		System.out.println(encodeBase(a7));
		System.out.println(encodeBase(a8));
		System.out.println(encodeBase(a9));
		System.out.println(encodeBase(a10));
		System.out.println("");
		System.out.println(decodeBase("U29mdHdhcmUgU3lzdGVtcw=="));
		System.out.println(encodeBase("Software Systems"));
		//End of decoding with Base64
	}
}
