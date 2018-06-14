package tdp.vip.dblocal;

import android.graphics.Bitmap;

import java.util.Vector;

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
    }

    private void initFamosos() {
        Famoso messi = new Famoso( "messi", "Lionel Messi", "La pulga");
        Famoso ronaldo = new Famoso( "ronaldo", "Ronaldo Luís Nazário de Lima", "O Fenômeno");
        Famoso justin = new Famoso( "justin", "Justin Bieber", null);
        Famoso lali = new Famoso( "lali", "Mariana Esposito ", "Lali");

        famosos.add(messi);
        famosos.add(ronaldo);
        famosos.add(justin);
        famosos.add(lali);

        famososFutbol.add(messi);
        famososFutbol.add(ronaldo);
        famososMusica.add(lali);
        famososMusica.add(justin);
    }
}
