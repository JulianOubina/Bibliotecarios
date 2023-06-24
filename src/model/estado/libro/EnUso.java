package model.estado.libro;

import model.Libro;
import model.estado.StateLibro;

public class EnUso implements StateLibro {

    @Override
    public void devolver(Libro libro) {
        System.out.println("El libro se ha devuelto");
        libro.setEstadoLibro(new Disponible());
    }

    @Override
    public void prestar(Libro libro) {
        System.out.println("El libro ya se encuentra prestado");
    }

    @Override
    public boolean permitePrestamo() {
        return false;
    }

    @Override
    public String getDescripcion() {
        return "En Uso";
    }


}
