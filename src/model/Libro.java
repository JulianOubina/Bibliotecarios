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



    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    public void setDiasParaDevolver(int diasParaDevolver) {
        this.diasParaDevolver = diasParaDevolver;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setFechaPublicacion(String fechaPublicacionString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fechaPublicacion = LocalDate.parse(fechaPublicacionString, formatter);
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setEstadoLibro(StateLibro estadoLibro) {
        this.estadoLibro = estadoLibro;
    }
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
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
    public int getDiasParaDevolver() {
        return diasParaDevolver;
    }
    public String getUbicacion(){
        return ubicacion;
    }
}



