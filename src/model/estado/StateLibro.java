package model.estado;

import model.Libro;

public interface StateLibro {

    void devolver(Libro libro);
    void prestar(Libro libro);
    boolean permitePrestamo();
    String getDescripcion();
}
