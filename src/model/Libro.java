package model;

import model.estado.StateLibro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Libro {
    private int isbn;
    private StateLibro estadoLibro;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    private String categoria;
    private int diasParaDevolver;
    private String ubicacion;

    public Libro(int isbn, StateLibro estadoLibro, String titulo, String autor, LocalDate fechaPublicacion, String categoria, int diasParaDevolver, String ubicacion) {
        this.isbn = isbn;
        this.estadoLibro = estadoLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.categoria = categoria;
        this.diasParaDevolver = diasParaDevolver;
        this.ubicacion = ubicacion;
    }


    public void setEstadoLibro(StateLibro estadoLibro) {
        this.estadoLibro = estadoLibro;
    }

    public int getIsbn() {
        return isbn;
    }
    public StateLibro getEstadoLibro() {
        return estadoLibro;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getUbicacion(){
        return ubicacion;
    }
}



