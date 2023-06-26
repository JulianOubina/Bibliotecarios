package Controller;

import model.Libro;
import model.Prestamo;
import model.Socio;
import model.estado.libro.Disponible;
import model.estado.prestamo.AlDia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bibliotecario {

    public void generarSocio(SocioCRUD crudSocio){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean existe = crudSocio.existeSocio(id);

        if(existe){
            System.out.println("Ya existe un socio con ese id");
            return;
        }

        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("Dni: ");
        int dni = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Telefono: ");
        int telefono = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Medio de comunicación: ");
        String medioComunicacion = scanner.nextLine();

        Socio socio = new Socio(id, nombre, apellido, dni, email, telefono, medioComunicacion, 0);
        crudSocio.agregarSocio(socio);
    }
    public void eliminarSocio(SocioCRUD crudSocio){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id: ");
        int idEliminar = scanner.nextInt();

        crudSocio.eliminarSocio(idEliminar);
    }
    public void actualizarSocio(SocioCRUD crudSocio){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id: ");
        int idActualizar = scanner.nextInt();

        crudSocio.actualizarSocio(idActualizar);
    }
    public void mostrarSocios(SocioCRUD crudSocio){
        crudSocio.mostrarSocios();
    }


    public void crearEjemplar(LibroCRUD crudLibro){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ISBN: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();

        boolean existe = crudLibro.existeISBN(isbn);

        if (existe){
            System.out.println("Ya existe un ejemplar con ese ISBN");
            return;
        }

        System.out.println("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.println("Autor: ");
        String autor = scanner.nextLine();

        System.out.println("Fecha de Publicación: ");
        String fechaPublicacionString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaPublicacion = LocalDate.parse(fechaPublicacionString, formatter);

        System.out.println("Categoria: ");
        String categoria = scanner.nextLine();

        int diasParaDevolver;
        if (categoria == "Libro") {
            diasParaDevolver = 10;
        } else {
            diasParaDevolver = 5;
        }

        System.out.println("Ubicacion : "); //aca iria un adapter con el sistema externo
        String ubicacion = scanner.nextLine();


        Libro libro = new Libro(isbn, new Disponible(),  titulo, autor, fechaPublicacion, categoria, diasParaDevolver, ubicacion);
        crudLibro.agregarEjemplar(libro);
    }
    public void eliminarEjemplar(LibroCRUD crudLibro){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ISBN: ");
        int isbnEliminar = scanner.nextInt();
        scanner.nextLine();
        crudLibro.eliminarEjemplar(isbnEliminar);
    }


    public void buscarPorISBN(LibroCRUD crudLibro){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ISBN: ");
        int isbnBuscar = scanner.nextInt();
        scanner.nextLine();

        Libro libroEncontradoPorISBN = crudLibro.buscarEjemplarPorISBN(isbnBuscar);
        if (libroEncontradoPorISBN == null){
            System.out.println("No se encontraron ejemplares con ese ISBN");
        }else {
            System.out.println("----------------------------------");
            System.out.println("Titulo: " + libroEncontradoPorISBN.getTitulo());
            System.out.println("Autor: " + libroEncontradoPorISBN.getAutor());
            System.out.println("Fecha publicación: " + libroEncontradoPorISBN.getFechaPublicacion());
            System.out.println("Categoria: " + libroEncontradoPorISBN.getCategoria());
            System.out.println("Estado: " + libroEncontradoPorISBN.getEstadoLibro().getDescripcion());
            System.out.println("Ubicacion: " + libroEncontradoPorISBN.getUbicacion());
            System.out.println("----------------------------------");
        }
    }
    public void buscarPorTitulo(LibroCRUD crudLibro){
        Scanner scanner = new Scanner(System.in);
        List<Libro> librosEncontradosPorTitulo = new ArrayList<>();
        System.out.println("Titulo: ");
        String tituloBuscar = scanner.nextLine();

        librosEncontradosPorTitulo = crudLibro.buscarEjemplarPorTitulo(tituloBuscar);
        if (librosEncontradosPorTitulo.isEmpty()){
            System.out.println("No se encontraron ejemplares con ese Titulo");
        } else {
            for (Libro libro : librosEncontradosPorTitulo) {
                System.out.println("----------------------------------");
                System.out.println("ISBN: " + libro.getIsbn());
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Fecha publicación: " + libro.getFechaPublicacion());
                System.out.println("Categoria: " + libro.getCategoria());
                System.out.println("Estado: " + libro.getEstadoLibro().getDescripcion());
                System.out.println("Ubicacion: " + libro.getUbicacion());
                System.out.println("----------------------------------");
            }
        }
    }
    public void buscarPorAutor(LibroCRUD crudLibro){
        Scanner scanner = new Scanner(System.in);

        List<Libro> librosEncontradosPorAutor = new ArrayList<>();
        System.out.println("Autor: ");
        String autorBuscar = scanner.nextLine();
        librosEncontradosPorAutor = crudLibro.buscarEjemplarPorAutor(autorBuscar);
        if (librosEncontradosPorAutor.isEmpty()){
            System.out.println("No se encontraron ejemplares de ese Autor");
        } else {
            for (Libro libro : librosEncontradosPorAutor) {
                System.out.println("----------------------------------");
                System.out.println("ISBN: " + libro.getIsbn());
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Fecha publicación: " + libro.getFechaPublicacion());
                System.out.println("Categoria: " + libro.getCategoria());
                System.out.println("Estado: " + libro.getEstadoLibro().getDescripcion());
                System.out.println("Ubicacion: " + libro.getUbicacion());
                System.out.println("----------------------------------");
            }
        }
    }
    public void buscarPorCategoria(LibroCRUD crudLibro){
        Scanner scanner = new Scanner(System.in);
        List<Libro> librosEncontradosPorCategoria = new ArrayList<>();
        System.out.println("Categoria: ");
        String categoriaBuscar = scanner.nextLine();

        librosEncontradosPorCategoria = crudLibro.buscarEjemplarPorCategoria(categoriaBuscar);
        if (librosEncontradosPorCategoria.isEmpty()){
            System.out.println("No se encontraron ejemplares de esa Categoria");
        } else {
            for (Libro libro : librosEncontradosPorCategoria) {
                System.out.println("----------------------------------");
                System.out.println("ISBN: " + libro.getIsbn());
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Fecha publicación: " + libro.getFechaPublicacion());
                System.out.println("Categoria: " + libro.getCategoria());
                System.out.println("Estado: " + libro.getEstadoLibro().getDescripcion());
                System.out.println("Ubicacion: " + libro.getUbicacion());
                System.out.println("----------------------------------");
            }
        }
    }
    public void buscarPorFechas(LibroCRUD crudLibro){
        Scanner scanner = new Scanner(System.in);
        List<Libro> librosEncontradosPorFechas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Fecha Inicio: ");
        String fechaPublicacionStringIni = scanner.nextLine();
        LocalDate fechaPublicacionIni = LocalDate.parse(fechaPublicacionStringIni, formatter);

        System.out.println("Fecha Fin: ");
        String fechaPublicacionStringFin = scanner.nextLine();
        LocalDate fechaPublicacionFin = LocalDate.parse(fechaPublicacionStringFin, formatter);

        librosEncontradosPorFechas = crudLibro.buscarEjemplarPorFechas(fechaPublicacionIni, fechaPublicacionFin);
        if (librosEncontradosPorFechas.isEmpty()){
            System.out.println("No se encontraron ejemplares entre esas fechas");
        } else {
            for (Libro libro : librosEncontradosPorFechas) {
                System.out.println("----------------------------------");
                System.out.println("ISBN: " + libro.getIsbn());
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Fecha publicación: " + libro.getFechaPublicacion());
                System.out.println("Categoria: " + libro.getCategoria());
                System.out.println("Estado: " + libro.getEstadoLibro().getDescripcion());
                System.out.println("Ubicacion: " + libro.getUbicacion());
                System.out.println("----------------------------------");
            }
        }
    }


    public void generarPrestamo(LibroCRUD crudLibro, PrestamoCRUD crudPrestamo){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Isbn del libro: ");
        int isbnPrestamo = scanner.nextInt();
        scanner.nextLine();

        if(!crudLibro.disponibleISBN(isbnPrestamo)){
            System.out.println("No existe ese ISBN");
            return;
        }

        int diasRestantesPrestamo = crudLibro.calcularDiasRestantes(isbnPrestamo); //observer

        System.out.println("Id socio: ");
        int idSocioPrestamo = scanner.nextInt();
        scanner.nextLine();

        Socio socioPrestamo = SocioCRUD.buscarSocio(idSocioPrestamo);

        if (socioPrestamo == null){
            System.out.println("No existe el id");
            return;
        }

        if(!socioPrestamo.isHabilitado()){
            System.out.println("No esta habilitado, si desea generar el prestamo debera hablar con el bibliotecario");
            return;
        }

        diasRestantesPrestamo = diasRestantesPrestamo + socioPrestamo.getDiasExtraPrestamo();

        Prestamo prestamo = new Prestamo(new AlDia(), LocalDate.now(), diasRestantesPrestamo, isbnPrestamo, idSocioPrestamo);
        crudPrestamo.generarPrestamo(prestamo);
        crudLibro.prestarLibro(isbnPrestamo); //cambia el state a En uso
    }
    public void cancelarPrestamo(LibroCRUD crudLibro, SocioCRUD crudSocio, PrestamoCRUD crudPrestamo){
        Scanner scanner = new Scanner(System.in);

        System.out.println("ISBN del libro a devolver: ");
        int isbnPrestamoDevuelto = scanner.nextInt();
        scanner.nextLine();
        if(crudLibro.disponibleISBN(isbnPrestamoDevuelto)){
            System.out.println("No existe ese ISBN");
            return;
        }
        Prestamo prestamoBuscar = crudPrestamo.buscarPrestamo(isbnPrestamoDevuelto);
        Socio socio = crudSocio.buscarSocio(prestamoBuscar.getIdSocio());
        crudPrestamo.cancelarPrestamo(isbnPrestamoDevuelto, socio);
        crudLibro.devolverLibro(isbnPrestamoDevuelto); //cambia state a Disponible
    }
    public void actualizarPrestamo(LibroCRUD crudLibro, SocioCRUD crudSocio, PrestamoCRUD crudPrestamo){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Isbn del libro: ");
        int isbnPrestamoActualizar = scanner.nextInt();
        scanner.nextLine();

        if(crudLibro.disponibleISBN(isbnPrestamoActualizar)){
            System.out.println("No existe ese ISBN");
            return;
        }

        System.out.println("Id socio: ");
        int idSocioPrestamoActualizar = scanner.nextInt();
        scanner.nextLine();

        if (!crudSocio.existeSocio(idSocioPrestamoActualizar)){
            System.out.println("No existe el id");
            return;
        }
        crudPrestamo.actualizarPrestamo(idSocioPrestamoActualizar, isbnPrestamoActualizar);
    }
    public void mostrarPrestamos(PrestamoCRUD crudPrestamo){
        crudPrestamo.mostrarHistorial();
    }


    public void generarNotificaciones(Notificador notificador, PrestamoCRUD crudPrestamo){
        System.out.println("Notificaciones generadas");
        notificador.generarNotificacion(crudPrestamo.getPrestamos());
    }
    public void enviarNotificaciones(Notificador notificador){
        notificador.enviarNotificacion();
    }
}