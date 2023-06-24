package model.estado.libro;

import model.Libro;
import model.estado.StateLibro;

public class Disponible implements StateLibro {

    @Override
    public void devolver(Libro libro) {
        System.out.println("El libro ya se encuentra devuelto");
    }

    @Override
    public void prestar(Libro libro) {
        System.out.println("El libro se ha prestado");
        libro.setEstadoLibro(new EnUso());
    }

    @Override
    public boolean permitePrestamo() {
        return true;
    }

    @Override
    public String getDescripcion() {
        return "Disponible";
    }

}
