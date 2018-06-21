package tdp.vip.dblocal;

import android.graphics.Bitmap;

import java.util.Vector;

import tdp.vip.Util;

public class DBLocal {

    private static final DBLocal ourInstance = new DBLocal();
    public static DBLocal getInstance() {
        return ourInstance;
    }

    public Usuario usuarioApp;
    public Vector<Usuario> usuarios;
    public Vector<Publicacion> publicaciones;
    public Vector<Famoso> famosos;
    public Vector<Famoso> famososTV;
    public Vector<Famoso> famososMusica;
    public Vector<Famoso> famososFutbol;

    private DBLocal() {
        this.usuarios = new Vector<Usuario>();
        this.famosos = new Vector<Famoso>();
        this.famososTV = new Vector<Famoso>();
        this.famososMusica = new Vector<Famoso>();
        this.famososFutbol = new Vector<Famoso>();
        this.publicaciones = new Vector<Publicacion>();
        this.initDemo();
    }

    /**
     * Dada una id, busca un usuario en la base de datos
     * @param id            El id de usuario
     * @return              El usuario si lo encuentra, null sino
     */
    public Usuario getUsuario(int id) {
        for ( Usuario usuario : usuarios) {
            if (usuario.id == id) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Dada una id, busca un famoso
     * @param id            El id de usuario
     * @return              El usuario si lo encuentra, null sino
     */
    public Famoso getFamoso(int id) {
        for ( Famoso famoso : famosos) {
            if (famoso.id == id) {
                return famoso;
            }
        }
        return null;
    }

    /**
     * Dada una id, busca una publicacion en la base de datos
     * @param id            El id de usuario
     * @return              La publicacion si la encuentra, null sino
     */
    public Publicacion getPublicacion(int id) {
        for ( Publicacion publicacion : publicaciones) {
            if (publicacion.id == id) {
                return publicacion;
            }
        }
        return null;
    }

    /**
     * Dado el id de un famoso, devuelve todas las publicaciones que haya de este
     * @param idFamoso          El id del famoso
     * @return                  Vector de publicaciones
     */
    public Vector<Publicacion> getPublicacionesDeFamosoALaVenta(int idFamoso) {
        Vector<Publicacion> resultado = new Vector<Publicacion>();
        for ( Publicacion publicacion : publicaciones) {
            if (publicacion.idFamoso == idFamoso && publicacion.estado == EstadoPublicacion.A_LA_VENTA) {
                resultado.add(publicacion);
            }
        }
        return resultado;
    }

    /**
     * Dada una id, devuelve la prioridad de busqueda para las publicaciones del usuario
     * @param id            El id de usuario
     * @return              La prioridad si encuentra al usuario, 0 sino
     */
    public int getPrioridadBusqueda(int id) {
        Usuario usuario = getUsuario(id);
        if (usuario != null) return usuario.prioridadPublicaciones;
        return 0;
    }

    /**
     * Carga los datos por defecto para hacer la demo de la aplicacion
     */
    private void initDemo() {
        initFamosos();
        initPublicaciones();
    }

    private void initFamosos() {
        Famoso messi = new Famoso( Util.resStringToURI("drawable","f_messi"), "Lionel Messi", "La pulga");
        Famoso ronaldo = new Famoso( Util.resStringToURI("drawable","f_ronaldo"), "Cristiano Ronaldo", null);
        Famoso maradona = new Famoso( Util.resStringToURI("drawable","f_maradona"), "Diego Maradona", null);
        Famoso kun = new Famoso( Util.resStringToURI("drawable","f_kun"), "Kun Aguero", null);
        Famoso bianchi = new Famoso( Util.resStringToURI("drawable","f_bianchi"), "Carlos Bianchi", null);
        Famoso palermo = new Famoso( Util.resStringToURI("drawable","f_palermo"), "Martin Palermo", null);


        Famoso justin = new Famoso( Util.resStringToURI("drawable","f_justin"), "Justin Bieber", null);
        Famoso lali = new Famoso( Util.resStringToURI("drawable","f_lali"), "Lali Esposito ", "Lali");
        Famoso cerati = new Famoso( Util.resStringToURI("drawable","f_cerati"), "Gustavo Cerati", null);
        Famoso charlie = new Famoso( Util.resStringToURI("drawable","f_charlie"), "Charlie Garcia", null);
        Famoso michael = new Famoso( Util.resStringToURI("drawable","f_michael"), "Michael Jackson", null);
        Famoso elvis = new Famoso( Util.resStringToURI("drawable","f_elvis"), "Elvis Presley", null);

        Famoso darin = new Famoso( Util.resStringToURI("drawable","f_darin"), "Ricardo Darin", null);
        Famoso peretti = new Famoso( Util.resStringToURI("drawable","f_peretti"), "Diego Peretti", null);
        Famoso francella = new Famoso( Util.resStringToURI("drawable","f_francella"), "Guillermo Francella", null);
        Famoso echarri = new Famoso( Util.resStringToURI("drawable","f_echarri"), "Pablo Echarri", null);
        Famoso arana = new Famoso( Util.resStringToURI("drawable","f_arana"), "Facundo Arana", null);
        Famoso jeffbridges = new Famoso( Util.resStringToURI("drawable","f_jeff"), "Jeff Bridges", null);

        addFamosoFutbol(messi);
        addFamosoFutbol(maradona);
        addFamosoFutbol(ronaldo);
        addFamosoFutbol(bianchi);
        addFamosoFutbol(kun);
        addFamosoFutbol(palermo);

        addFamosoMusica(justin);
        addFamosoMusica(lali);
        addFamosoMusica(cerati);
        addFamosoMusica(charlie);
        addFamosoMusica(michael);
        addFamosoMusica(elvis);

        addFamosoTVCine(darin);
        addFamosoTVCine(peretti);
        addFamosoTVCine(francella);
        addFamosoTVCine(echarri);
        addFamosoTVCine(arana);
        addFamosoTVCine(jeffbridges);
    }

    private void addFamosoFutbol(Famoso famoso) {
        famosos.add(famoso);
        famososFutbol.add(famoso);
    }

    private void addFamosoMusica(Famoso famoso) {
        famosos.add(famoso);
        famososMusica.add(famoso);
    }

    private void addFamosoTVCine(Famoso famoso) {
        famosos.add(famoso);
        famososTV.add(famoso);
    }

    private void initPublicaciones() {
        // Las ids de los famosos matchean con el orden que se llamo new Famoso
        Publicacion remera = new Publicacion("Remera de la selección autografiada por Messi",
                "Oferta imperdible, remera de la selección Argentina firmada por Lionel Messi",
                Util.resStringToURI("drawable","p_messi_remera"), 5, 501532, 1);
        Publicacion cd = new Publicacion("Cd My World autografiado por Justin Bieber",
                "Album del músico Justin Bieber \" My World \" autografiado por el mismo",
                Util.resStringToURI("drawable","p_justin_cd"), 5, 180001, 7);

        Publicacion saludoPersonalizadoBianchi = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 500, 5);

        Publicacion saludoPersonalizadoMessi = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 500, 1);


        publicaciones.add(remera);
        publicaciones.add(cd);
        publicaciones.add(saludoPersonalizadoBianchi);
        publicaciones.add(saludoPersonalizadoMessi);
    }
}
