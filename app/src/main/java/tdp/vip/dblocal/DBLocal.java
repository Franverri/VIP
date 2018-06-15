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
        Famoso messi = new Famoso( Util.resStringToURI("drawable","messi"), "Lionel Messi", "La pulga");
        Famoso ronaldo = new Famoso( Util.resStringToURI("drawable","ronaldo"), "Ronaldo Luís Nazário de Lima", "O Fenômeno");
        Famoso justin = new Famoso( Util.resStringToURI("drawable","justin"), "Justin Bieber", null);
        Famoso lali = new Famoso( Util.resStringToURI("drawable","lali"), "Mariana Esposito ", "Lali");

        famosos.add(messi);
        famosos.add(ronaldo);
        famosos.add(justin);
        famosos.add(lali);

        famososFutbol.add(messi);
        famososFutbol.add(ronaldo);
        famososMusica.add(lali);
        famososMusica.add(justin);
    }

    private void initPublicaciones() {
        Publicacion remera = new Publicacion("Remera de la selección autografiada por Messi",
                "Oferta imperdible, remera de la selección Argentina firmada por Lionel Messi",
                Util.resStringToURI("drawable","remeramessi"), 5, 501532);
        Publicacion cd = new Publicacion("Cd My World autografiado por Justin Bieber",
                "Album del músico Justin Bieber \" My World \" autografiado por el mismo",
                Util.resStringToURI("drawable","cdjustin"), 5, 180001);

        Publicacion remera1 = new Publicacion("Remera de la selección autografiada por Messi",
                "Remera de la selección Argentina firmada por Lionel Messi",
                Util.resStringToURI("drawable","remeramessi"), 5, 501532);
        Publicacion remera2 = new Publicacion("Remera de la selección autografiada por Messi",
                "Remera de la selección Argentina firmada por Lionel Messi",
                Util.resStringToURI("drawable","remeramessi"), 5, 501532);
        Publicacion remera3 = new Publicacion("Remera de la selección autografiada por Messi",
                "Remera de la selección Argentina firmada por Lionel Messi",
                Util.resStringToURI("drawable","remeramessi"), 5, 501532);
        Publicacion remera4 = new Publicacion("Remera de la selección autografiada por Messi",
                "Remera de la selección Argentina firmada por Lionel Messi",
                Util.resStringToURI("drawable","remeramessi"), 5, 501532);

        publicaciones.add(remera);
        publicaciones.add(cd);

        publicaciones.add(remera1);
        publicaciones.add(remera2);
        publicaciones.add(remera3);
        publicaciones.add(remera4);
    }
}
