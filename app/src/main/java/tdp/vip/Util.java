package tdp.vip;

import android.net.Uri;
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

    /**
     * Dado un recurso estilo "@drawable/messi" nos devuelve la URI apropiada
     * @param tipo                  Tipo del recurso, por ej "drawable"
     * @param nombre                Nombre del recurso, por ej "messi"
     * @return                      La URI, no se chequea si es valida
     */
    public static Uri resStringToURI(String tipo, String nombre) {
        return Uri.parse("android.resource://tdp.vip/" + tipo + "/" + nombre);
    }

    /**
     * Dado un recurso estilo R.drawable.messi nos devuelve la URI apropiada
     * @param resource              El recurso como lo identifica android
     * @return                      La URI, no se chequea si es valida
     */
    public static Uri resourceToURI(int resource) {
        return Uri.parse("android.resource://tdp.vip/" + resource);
    }

    /**
     * Dado un int que representa un precio, lo convierte a string de precio, ej 1234 -> "$12.34"
     * @param precio        El int a convertir en precio
     * @return              El precio como string
     */
    public static String intAPrecio(int precio) {
        return String.format("$%d.%02d", precio/100, precio%100);
    }
}
