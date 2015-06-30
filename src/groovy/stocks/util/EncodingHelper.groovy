package stocks.util

import java.security.MessageDigest

/**
 * Created by farzin on 6/30/15.
 */
public class EncodingHelper {
    public static String MD5(String s) {
        MessageDigest.getInstance("MD5").digest(s.bytes).encodeHex().toString()
    }
}
