package tdp.vip.dblocal;

import android.graphics.Bitmap;

public class Famoso {
    public static int contadorIDs = 1;
    public int id;
    public String nombreYApellido = null;
    public String alias = null;
    public String fotoResPath = null;

    /**
     * Constructor
     * @param fotoResPath       Path al recurso de imagen, por ejemplo @drawable/ronaldo
     * @param nombreYApellido   Nombre y apellido del famoso, puede ser null
     * @param alias             Alias o nombre artistico del famoso, puede ser null
     */
    public Famoso (String fotoResPath, String nombreYApellido, String alias) {
        this.id = Famoso.contadorIDs++;
        this.nombreYApellido = nombreYApellido;
        this.alias = alias;
        this.fotoResPath = fotoResPath;
    }
}

