package Controller;
import java.time.LocalDate;
import java.util.*;
import model.Libro;

public class LibroCRUD {

    private static List<Libro> libros;

    public LibroCRUD(){
        libros = new ArrayList<>();
    }

    public void agregarEjemplar(Libro libro){
        libros.add(libro);
        System.out.println("Ejemplar ingresado con exito");
    }

    public void eliminarEjemplar(int isbn){
        boolean entro = false;
        Iterator<Libro> iter = libros.iterator();
        while(iter.hasNext()){
            Libro libro =iter.next();
            if(libro.getIsbn() == isbn){
                iter.remove();
                entro = true;
                break;
            }
        }
        if (entro){
            System.out.println("El ejemplar se elimino con exito");
        }
        else {
            System.out.println("No se encontro un ejemplar con ese ISBN");
        }
    }

    public Libro buscarEjemplarPorISBN(int isbn){

        for(Libro libro : libros){
            if(libro.getIsbn() == isbn){
                return libro;
            }
        }
        return null;
    }

    public static List<Libro> buscarEjemplarPorTitulo(String titulo){
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros){
            if (libro.getTitulo().trim().equalsIgnoreCase(titulo.trim())){
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> buscarEjemplarPorAutor(String autor){
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros){
            if(libro.getAutor().trim().equalsIgnoreCase(autor.trim())){
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> buscarEjemplarPorCategoria(String categoria){
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros){
            if(libro.getCategoria().trim().equalsIgnoreCase(categoria.trim())){
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public List<Libro> buscarEjemplarPorFechas(LocalDate fechaIni, LocalDate fechaFin){
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros){
            LocalDate fechaPublicacion = libro.getFechaPublicacion();

            if(fechaPublicacion.isAfter(fechaIni) && fechaPublicacion.isBefore(fechaFin)){
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public boolean disponibleISBN(int isbn){
        for(Libro libro : libros){
            if(libro.getIsbn() == isbn  && libro.getEstadoLibro().permitePrestamo()){
                return true;
            }
        }
        return false;
    }

    public boolean existeISBN(int isbn){
        for (Libro libro : libros){
            if (libro.getIsbn() == isbn){
                return true;
            }
        }
        return false;
    }

    public int calcularDiasRestantes(int isbn){
        int diasParaDevolver = 0;
        for(Libro libro : libros){
            if(libro.getIsbn() == isbn && libro.getEstadoLibro().permitePrestamo()){
                if (libro.getCategoria() == "Libro"){
                    diasParaDevolver = 10;
                }else {
                    diasParaDevolver = 5;
                }
            }
        }
        return diasParaDevolver;
    }

    public void prestarLibro(int isbn){
        for(Libro libro : libros){
            if(libro.getIsbn() == isbn){
                libro.getEstadoLibro().prestar(libro);
            }
        }
    }

    public void devolverLibro(int isbn){
        for (Libro libro : libros){
            if(libro.getIsbn() == isbn){
                libro.getEstadoLibro().devolver(libro);
            }
        }
    }

    public List<Libro> getLibros(){
        return libros;
    }
}
