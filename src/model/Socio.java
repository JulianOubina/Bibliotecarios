package model;

import java.util.*;
public class Socio {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private String email;
    private int telefono;
    private String medioComunicacion;
    private boolean habilitado;
    private int prestamoExitoso;
    private int diasExtraPrestamo;

    public Socio(int id, String nombre, String apellido, int dni, String email, int telefono, String medioComunicacion, int prestamoExitoso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.medioComunicacion = medioComunicacion;
        this.habilitado = true;
        this.prestamoExitoso = prestamoExitoso;
        diasExtraPrestamo = 0;
    }


    public int getPrestamoExitoso() {
        return prestamoExitoso;
    }
    public int getDiasExtraPrestamo() {
        return diasExtraPrestamo;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getDni() {
        return dni;
    }
    public String getApellido() {
        return apellido;
    }
    public String getEmail() {
        return email;
    }
    public int getTelefono() {
        return telefono;
    }
    public String getMedioComunicacion() {
        return medioComunicacion;
    }
    public boolean isHabilitado() {
        return habilitado;
    }


    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public void setMedioComunicacion(String medioComunicacion) {
        this.medioComunicacion = medioComunicacion;
    }
    public void setPrestamoExitoso(int prestamoExitoso) {
        this.prestamoExitoso = prestamoExitoso;
    }
    public void setDiasExtraPrestamo(int diasExtraPrestamo) {
        this.diasExtraPrestamo = diasExtraPrestamo;
    }
}

