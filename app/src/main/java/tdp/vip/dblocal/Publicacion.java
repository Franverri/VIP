package tdp.vip.dblocal;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.Date;

public class Publicacion {
    public static int contadorPublicaciones = 1;
    public int id;
    public String titulo = null;
    public int idFamoso;
    public int precio;
    public int comision;
    public EstadoPublicacion estado;
    public int prioridadBusqueda;
    public Date fecha;
    public Uri fotoURI;
    public int idUsuarioPublicador;
    public String descripcion;

    /**
     * Crea una nueva publicacion
     * @param titulo                        Titulo de la publicacion
     * @param descripcion                   Descripcion de la publicacion
     * @param fotoURI                       Uri a la foto
     * @param idUsuarioPublicador           Id del publicador
     * @param precio                        Precio. Se espera un entero con los centavos incluidos, ej $1.00 = 100.
     */
    public Publicacion (String titulo, String descripcion, Uri fotoURI, int idUsuarioPublicador, int precio) {
        this.id = Publicacion.contadorPublicaciones++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fotoURI = fotoURI;
        this.estado = EstadoPublicacion.A_LA_VENTA;
        this.idUsuarioPublicador = idUsuarioPublicador;
        this.precio = precio;
        this.comision = calcularComision();
        this.fecha = new Date();
        this.prioridadBusqueda = 0; //TODO: DBLocal.getInstance().getPrioridadBusqueda(idUsuarioPublicador);
    }

    /**
     * Calcula la comision
     * TODO: Un calculo mejor
     */
    public int calcularComision () {
        return (int) ((float) precio * 0.05);
    }
}
