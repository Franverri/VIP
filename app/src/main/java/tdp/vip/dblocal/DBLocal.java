package tdp.vip.dblocal;

import android.graphics.Bitmap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * Verifica si un nombre de usuario ya existe
     * @param nombreUsuario     El nombre de usuario
     * @return                  True si existe, false sino
     */
    public Boolean nombreUsuarioExiste(String nombreUsuario) {
        for ( Usuario usuario : usuarios) {
            if (usuario.nombreUsuario.equals(nombreUsuario)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Dado un usr y password devuelve el usuario correcto si este existe
     * @param nombreUsuario     Nombre de usuario
     * @param password          Password en texto plano
     * @return                  El usuario si lo encuentra, null sino
     */
    public Usuario testLogin(String nombreUsuario, String password) {
        byte[] passwordHash = Util.stringToHash(password);
        for ( Usuario usuario : usuarios) {
            if (usuario.nombreUsuario.equals(nombreUsuario) && java.util.Arrays.equals(usuario.passwordHash , passwordHash) ) {
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
        initUsuarios();
        initFamosos();
        initPublicaciones();
    }

    private void initFamosos() {
        Famoso messi = new Famoso( Util.resStringToURI("drawable","f_messi"), "Lionel Messi", "La pulga");
        Famoso ronaldo = new Famoso( Util.resStringToURI("drawable","f_ronaldo"), "Cristiano Ronaldo", null);
        Famoso maradona = new Famoso( Util.resStringToURI("drawable","f_maradona"), "Diego Maradona", null);
        Famoso kun = new Famoso( Util.resStringToURI("drawable","f_kun"), "Kun Aguero", null);
        Famoso bianchi = new Famoso( Util.resStringToURI("drawable","f_bianchi"), "Carlos Bianchi", null);
        Famoso gago = new Famoso( Util.resStringToURI("drawable","f_gago"), "Fernando Gago", "Pintita");


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
        addFamosoFutbol(gago);

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
                Util.resStringToURI("drawable","p_messi_remera"), 5, 1000000, 1);

        Publicacion cd = new Publicacion("Cd My World autografiado por Justin Bieber",
                "Album del músico Justin Bieber \" My World \" autografiado por el mismo",
                Util.resStringToURI("drawable","p_justin_cd"), 5, 400000, 7);

        Publicacion rmgago = new Publicacion("Remera del Real Madrid autografiada",
                "Remera del Real Madrid utilizada por Fernando Gago en el año",
                Util.resStringToURI("drawable","p_rmgago"), 5, 5000000, 6);

        //Saludo personalizado de todos
        Publicacion saludoPersonalizado1 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 1);

        Publicacion saludoPersonalizado2 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 2);

        Publicacion saludoPersonalizado3 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 3);

        Publicacion saludoPersonalizado4 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 4);

        Publicacion saludoPersonalizado5 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 5);

        Publicacion saludoPersonalizado6 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 6);

        Publicacion saludoPersonalizado7 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 7);

        Publicacion saludoPersonalizado8 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 8);

        Publicacion saludoPersonalizado9 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 9);

        Publicacion saludoPersonalizado10 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 10);

        Publicacion saludoPersonalizado11 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 11);

        Publicacion saludoPersonalizado12 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 12);

        Publicacion saludoPersonalizado13 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 13);

        Publicacion saludoPersonalizado14 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 14);

        Publicacion saludoPersonalizado15 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 15);

        Publicacion saludoPersonalizado16 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 16);

        Publicacion saludoPersonalizado17 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 17);

        Publicacion saludoPersonalizado18 = new Publicacion("Saludo personalizado", "Video personalizado para la persona indicada",
                Util.resStringToURI("drawable", "p_video_personalizado"), 5, 50000, 18);

        publicaciones.add(saludoPersonalizado1);
        publicaciones.add(saludoPersonalizado2);
        publicaciones.add(saludoPersonalizado3);
        publicaciones.add(saludoPersonalizado4);
        publicaciones.add(saludoPersonalizado5);
        publicaciones.add(saludoPersonalizado6);
        publicaciones.add(saludoPersonalizado7);
        publicaciones.add(saludoPersonalizado8);
        publicaciones.add(saludoPersonalizado9);
        publicaciones.add(saludoPersonalizado10);
        publicaciones.add(saludoPersonalizado11);
        publicaciones.add(saludoPersonalizado12);
        publicaciones.add(saludoPersonalizado13);
        publicaciones.add(saludoPersonalizado14);
        publicaciones.add(saludoPersonalizado15);
        publicaciones.add(saludoPersonalizado16);
        publicaciones.add(saludoPersonalizado17);
        publicaciones.add(saludoPersonalizado18);

        publicaciones.add(remera);
        publicaciones.add(cd);
        publicaciones.add(rmgago);
    }

    public void updateUsuario(String nombreUsr, String nombre, String apellido, String mail, String fechaNacimiento){

        for( Usuario usr : usuarios ) {
                if(usr.nombreUsuario.equals(nombreUsr)) {
                    usr.nombre = nombre;
                    usr.apellido = apellido;
                    usr.email = mail;
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    usr.fechaNacimiento = date;
                }
        }
    }

    private void initUsuarios() {

        Usuario usr1 = new Usuario("franco", "Franco", "Etcheverri", "franco@example.com", "1/1/1990", "1234");
        Usuario usr2 = new Usuario("francisco", "Francisco", "Marin", "francisco@example.com", "1/1/1990", "1234");
        Usuario usr3 = new Usuario("agustin", "Agustin", "Luques", "agustin@example.com", "1/1/1990", "1234");
        Usuario usr4 = new Usuario("diego", "Diego", "Martins Forgan", "diego@example.com", "1/1/1990", "1234");
        Usuario usr5 = new Usuario("guido", "Guido", "Negri", "guido@example.com", "1/1/1990", "1234");
        Usuario usr6 = new Usuario("octavio", "Octavio", "Iogha", "octavio@example.com", "1/1/1990", "1234");
        Usuario usr7 = new Usuario("usr7", "Juan", "Garcia", "usr7@example.com", "1/1/1990", "1234");
        Usuario usr8 = new Usuario("usr8", "Martin", "Gomez", "usr8@example.com", "1/1/1990", "1234");
        Usuario usr9 = new Usuario("usr9", "Luis", "Alonzo", "usr9@example.com", "1/1/1990", "1234");

        usuarios.add(usr1);
        usuarios.add(usr2);
        usuarios.add(usr3);
        usuarios.add(usr4);
        usuarios.add(usr5);
        usuarios.add(usr6);
        usuarios.add(usr7);
        usuarios.add(usr8);
        usuarios.add(usr9);
        
    }
}
