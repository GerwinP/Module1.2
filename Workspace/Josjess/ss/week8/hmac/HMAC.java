package ss.week8.hmac;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMAC {
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final String KEY_STRING = "0xabababababababab";
    private Mac mac;


    public HMAC() {
        try {
            mac = Mac.getInstance(HMAC_ALGORITHM);
            byte[] keyBytes = KEY_STRING.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_ALGORITHM);
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        byte[] messageMac = mac.doFinal("s1483870".getBytes());
        String calculated = Hex.encodeHexString(mac.doFinal("s1483870".getBytes()));

        System.out.println(Hex.encodeHexString(messageMac));
    }


    public static void main(String[] args) {
        new HMAC().run();
    }

}
