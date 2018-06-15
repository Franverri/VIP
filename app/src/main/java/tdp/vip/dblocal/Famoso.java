package tdp.vip.dblocal;

import android.net.Uri;

public class Famoso {
    public static int contadorIDs = 1;
    public int id;
    public String nombreYApellido = null;
    public String alias = null;
    public Uri fotoURI = null;

    /**
     * Constructor
     * @param fotoUri           URI hacia la imagen
     * @param nombreYApellido   Nombre y apellido del famoso, puede ser null
     * @param alias             Alias o nombre artistico del famoso, puede ser null
     */
    public Famoso (Uri fotoUri, String nombreYApellido, String alias) {
        this.id = Famoso.contadorIDs++;
        this.nombreYApellido = nombreYApellido;
        this.alias = alias;
        this.fotoURI = fotoUri;
    }
}

