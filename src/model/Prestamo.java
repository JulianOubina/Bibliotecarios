package model;

import model.estado.StatePrestamo;
import model.estado.prestamo.AlDia;

import java.time.LocalDate;

public class Prestamo {
    private StatePrestamo estadoPrestamo;
    private LocalDate diaPrestamo;
    private int diasRestantes;
    private int isbn;
    private int idSocio;

    public Prestamo(StatePrestamo estadoPrestamo, LocalDate diaPrestamo, int diasRestantes, int isbn, int idSocio) {
        this.estadoPrestamo = estadoPrestamo;
        this.diaPrestamo = diaPrestamo;
        this.diasRestantes = diasRestantes;
        this.isbn = isbn;
        this.idSocio = idSocio;
    }

    public int getIsbn() {
        return isbn;
    }
    public StatePrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }
    public LocalDate getDiaPrestamo() {
        return diaPrestamo;
    }
    public int getIdSocio() {
        return idSocio;
    }
    public int getDiasRestantes() {
        return diasRestantes;
    }
    public void setEstadoPrestamo(StatePrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }
    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

}
