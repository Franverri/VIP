package tdp.vip.dblocal;

import java.util.Date;

import tdp.vip.Util;

public class Usuario {
    public static int contadorUsuarios = 1;
    public int id;
    public String email;
    public byte[] passwordHash;
    public Date fechaRegistro;
    public EstadoCuenta estadoCuenta;
    public int prioridadPublicaciones;

    public Usuario (String email, byte[] passwordHash) {
        this.id = Usuario.contadorUsuarios++;
        this.email = email;
        this.passwordHash = passwordHash;
        this.estadoCuenta = EstadoCuenta.PENDIENTE_VERIFICACION;
        this.fechaRegistro = new Date();
        this.prioridadPublicaciones = 0;
    }


    public Usuario (String email, String passwordTextoPlano) {
        this(email, Util.stringToHash(passwordTextoPlano)  );
    }


}
