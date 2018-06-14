package tdp.vip.dblocal;

import android.graphics.Bitmap;

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
    public String fotoResPath;
    public int idUsuarioPublicador;
    public String descripcion;

    public Publicacion (String titulo, String descripcion, String fotoResPath, int idUsuarioPublicador, int precio) {
        this.id = Publicacion.contadorPublicaciones++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fotoResPath = fotoResPath;
        this.estado = EstadoPublicacion.PENDIENTE_APROBACION;
        this.idUsuarioPublicador = idUsuarioPublicador;
        this.precio = precio;
        this.comision = calcularComision();
        this.fecha = new Date();
        this.prioridadBusqueda = DBLocal.getInstance().getPrioridadBusqueda(idUsuarioPublicador);
    }

    /**
     * Calcula la comision
     * TODO: Un calculo mejor
     */
    public int calcularComision () {
        return (int) ((float) precio * 0.05);
    }
}
