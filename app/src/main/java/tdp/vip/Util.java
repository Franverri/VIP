package tdp.vip;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Funciones de utilidad varia
 */
public class Util {

    /**
     * Convierte un string a un hash de 256 bits
     * @param string                        El string en cuestion
     * @return                              Un byte array de 32 bytes
     */
    public static byte[] stringToHash(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(string.getBytes());
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
