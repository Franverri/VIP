package tdp.vip.dblocal;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tdp.vip.Util;

public class Usuario {
    public static int contadorUsuarios = 1;
    public int id;
    public String email;
    public String nombreUsuario;
    public String nombre;
    public String apellido;
    public byte[] passwordHash;
    public Date fechaRegistro;
    public Date fechaNacimiento;
    public EstadoCuenta estadoCuenta;
    public int prioridadPublicaciones;

    public Usuario (String nombreUsuario, String nombre, String apellido, String email, Date fechaNacimiento, byte[] passwordHash) {
        this.id = Usuario.contadorUsuarios++;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.passwordHash = passwordHash;
        this.estadoCuenta = EstadoCuenta.PENDIENTE_VERIFICACION;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = new Date();
        this.prioridadPublicaciones = 0;
    }


    /**
     * Constructor simplificado al que se le pasan textos planos
     */
    public Usuario (String nombreUsuario, String nombre, String apellido, String email, String fechaNacimiento, String passwordTextoPlano) {
        this(nombreUsuario, nombre, apellido, email, parseFecha(fechaNacimiento), Util.stringToHash(passwordTextoPlano)  );
    }

    /**
     * Constructor simplificado al que se le pasan textos planos pero fecha como Date
     */
    public Usuario (String nombreUsuario, String nombre, String apellido, String email, Date fechaNacimiento, String passwordTextoPlano) {
        this(nombreUsuario, nombre, apellido, email, fechaNacimiento, Util.stringToHash(passwordTextoPlano)  );
    }

    private static Date parseFecha (String fecha) {
        try {
            return new SimpleDateFormat("dd/mm/yyyy").parse(fecha);
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
