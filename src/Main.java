import Controller.LibroCRUD;
import Controller.Notificador;
import Controller.PrestamoCRUD;
import Controller.SocioCRUD;
import model.Prestamo;
import model.Socio;
import model.Libro;
import model.estado.StateLibro;
import model.estado.StatePrestamo;
import model.estado.libro.Disponible;
import model.estado.libro.*;
import model.estado.prestamo.AlDia;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SocioCRUD crudSocio = new SocioCRUD();
        LibroCRUD crudLibro = new LibroCRUD();
        PrestamoCRUD crudPrestamo = new PrestamoCRUD();
        Notificador notificador = new Notificador();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int opcionUsuario;

        generarSocios(crudSocio);
        generarLibros(crudLibro);
        generarPrestamos(crudPrestamo, crudLibro);

        do {
            System.out.println("Ingrese una opción (1-6): 1) Administrar Socio - 2) Administrar ejemplar - 3) Buscar Ejemplar - 4) Administrar Prestamo - 5) Historial de Prestamos - 6) Notificaciones");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese una opcion (1-3): 1) Crear socio - 2) Eliminar Socio - 3) Actualizar Socio - 4) Mostrar socios");
                    opcionUsuario = scanner.nextInt();

                    switch (opcionUsuario) {
                        case 1:
                            System.out.println("Id: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();

                            boolean existe = crudSocio.existeSocio(id);

                            if(existe){
                                System.out.println("Ya existe un socio con ese id");
                                break;
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
                            break;

                        case 2:
                            System.out.println("Id: ");
                            int idEliminar = scanner.nextInt();

                            crudSocio.eliminarSocio(idEliminar);
                            break;

                        case 3:
                            System.out.println("Id: ");
                            int idActualizar = scanner.nextInt();

                            crudSocio.actualizarSocio(idActualizar);
                            break;
                        case 4:
                            crudSocio.mostrarSocios();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Ingrese una opcion(1-2): 1) Crear Ejemplar - 2) Eliminar Ejemplar");
                    opcionUsuario = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcionUsuario) {
                        case 1:
                            System.out.println("ISBN: ");
                            int isbn = scanner.nextInt();
                            scanner.nextLine();

                            boolean existe = crudLibro.existeISBN(isbn);

                            if (existe){
                                System.out.println("Ya existe un ejemplar con ese ISBN");
                                break;
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

                            System.out.println("Ubicacion : ");
                            String ubicacion = scanner.nextLine();


                            Libro libro = new Libro(isbn, new Disponible(),  titulo, autor, fechaPublicacion, categoria, diasParaDevolver, ubicacion);
                            crudLibro.agregarEjemplar(libro);

                            break;

                        case 2:

                            System.out.println("ISBN: ");
                            int isbnEliminar = scanner.nextInt();
                            scanner.nextLine();
                            crudLibro.eliminarEjemplar(isbnEliminar);

                            break;
                    }
                    break;
                case 3:
                    System.out.println("Ingrese una opcion(1-5): 1) Buscar por ISBN - 2) Buscar por Titulo - 3) Buscar por Autor - 4) Buscar por categoria - 5) Buscar entre fechas");
                    opcionUsuario = scanner.nextInt();

                    scanner.nextLine();

                    switch (opcionUsuario) {
                        case 1:
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
                                System.out.println("----------------------------------");
                            }
                            break;
                        case 2:
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
                                    System.out.println("----------------------------------");
                                }
                            }
                            break;
                        case 3:
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
                                    System.out.println("----------------------------------");
                                }
                            }
                            break;
                        case 4:
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
                                    System.out.println("----------------------------------");
                                }
                            }
                            break;
                        case 5:
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
                                    System.out.println("----------------------------------");
                                }
                            }
                    }
                    break;
                case 4:
                    System.out.println("Ingrese una opcion(1-2): 1) Generar Prestamo - 2) Cancelar Prestamo - 3) Actualizar Prestamo - 4) Mostrar Prestamos");
                    opcionUsuario = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcionUsuario) {
                        case 1:

                            System.out.println("Isbn del libro: ");
                            int isbnPrestamo = scanner.nextInt();
                            scanner.nextLine();

                            if(!crudLibro.disponibleISBN(isbnPrestamo)){
                                System.out.println("No existe ese ISBN");
                                break;
                            }

                            int diasRestantesPrestamo = crudLibro.calcularDiasRestantes(isbnPrestamo);
                            System.out.println("Id socio: ");
                            int idSocioPrestamo = scanner.nextInt();
                            scanner.nextLine();

                            Socio socioPrestamo = SocioCRUD.buscarSocio(idSocioPrestamo);

                            if (socioPrestamo == null){
                                System.out.println("No existe el id");
                                break;
                            }

                            if(!socioPrestamo.isHabilitado()){
                                System.out.println("No esta habilitado, si desea generar el prestamo debera hablar con el bibliotecario");
                                break;
                            }

                            diasRestantesPrestamo = diasRestantesPrestamo + socioPrestamo.getDiasExtraPrestamo();

                            Prestamo prestamo = new Prestamo(new AlDia(), LocalDate.now(), diasRestantesPrestamo, isbnPrestamo, idSocioPrestamo);
                            crudPrestamo.generarPrestamo(prestamo);
                            crudLibro.prestarLibro(isbnPrestamo);
                            break;
                        case 2:
                            System.out.println("ISBN del libro a devolver: ");
                            int isbnPrestamoDevuelto = scanner.nextInt();
                            scanner.nextLine();
                            if(crudLibro.disponibleISBN(isbnPrestamoDevuelto)){
                                System.out.println("No existe ese ISBN");
                                break;
                            }
                            Prestamo prestamoBuscar = crudPrestamo.buscarPrestamo(isbnPrestamoDevuelto);
                            Socio socio = crudSocio.buscarSocio(prestamoBuscar.getIdSocio());
                            crudPrestamo.cancelarPrestamo(isbnPrestamoDevuelto, socio);
                            crudLibro.devolverLibro(isbnPrestamoDevuelto);
                            break;
                        case 3:
                            System.out.println("Isbn del libro: ");
                            int isbnPrestamoActualizar = scanner.nextInt();
                            scanner.nextLine();

                            if(crudLibro.disponibleISBN(isbnPrestamoActualizar)){
                                System.out.println("No existe ese ISBN");
                                break;
                            }

                            System.out.println("Id socio: ");
                            int idSocioPrestamoActualizar = scanner.nextInt();
                            scanner.nextLine();

                            if (!crudSocio.existeSocio(idSocioPrestamoActualizar)){
                                System.out.println("No existe el id");
                                break;
                            }
                            crudPrestamo.actualizarPrestamo(idSocioPrestamoActualizar, isbnPrestamoActualizar);
                            break;
                        case 4:
                            crudPrestamo.mostrarPrestamos();
                    }
                    break;
                case 5:
                    crudPrestamo.mostrarHistorial();
                    break;
                case 6:
                    //Opcion 1: generar notificaciones
                    //Opciones 2: enviarlas
                    notificador.generarNotificacion(crudPrestamo.getPrestamos());
                    notificador.enviarNotificacion();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 6.");
                    break;
            }
        }
        while(opcion > 0 && opcion < 7);
        System.out.println("Fin del programa");
    }

    private static void generarSocios(SocioCRUD crudSocio){
        Socio socio = new Socio(1, "Julian", "Alvarez", 44451620, "ja@prueba.com", 12345670, "wpp", 0);
        crudSocio.agregarSocio(socio);
        Socio socio2 = new Socio(2, "Lionel", "Messi", 44451621, "messi@prueba.com", 12345671, "mail", 0);
        crudSocio.agregarSocio(socio2);
        Socio socio3 = new Socio(3, "Dibu", "Martinez", 44451622, "dibu@prueba.com", 12345672, "sms", 0);
        crudSocio.agregarSocio(socio3);
        Socio socio4 = new Socio(4, "Otro", "Prueba", 44451623, "prueba@prueba.com", 12345673, "wpp", 0);
        crudSocio.agregarSocio(socio4);

    }

    private static void generarLibros(LibroCRUD crudLibro){
        Libro libro = new Libro(1, new Disponible(), "Cars", "Pixar", LocalDate.of(2002, 9, 11), "libros", 10, "Planta baja");
        crudLibro.agregarEjemplar(libro);
        Libro libro2 = new Libro(2, new Disponible(), "Toy story", "Disney", LocalDate.of(2002, 8, 11), "libros", 10, "Planta alta");
        crudLibro.agregarEjemplar(libro2);
        Libro libro3 = new Libro(3, new Disponible(), "Cuentos de bebe 1", "Pepito", LocalDate.of(2002, 7, 11), "revistas", 5, "Planta baja");
        crudLibro.agregarEjemplar(libro3);
        Libro libro4 = new Libro(4, new Disponible(), "Cuentos de bebe 2", "Pepito", LocalDate.of(2002, 6, 11), "magazines", 5, "Pasillo derecho");
        crudLibro.agregarEjemplar(libro4);
        Libro libro5 = new Libro(5, new Disponible(), "TeleTubbie", "Cartoon Network", LocalDate.of(2002, 5, 11), "libros", 10, "Pasillo izquierdo");
        crudLibro.agregarEjemplar(libro5);
    }

    private static void generarPrestamos(PrestamoCRUD crudPrestamo, LibroCRUD crudLibro){
        Prestamo prestamo = new Prestamo(new AlDia(), LocalDate.now(), 10, 1, 1);
        crudPrestamo.generarPrestamo(prestamo);
        crudLibro.prestarLibro(prestamo.getIsbn());
        Prestamo prestamo1 = new Prestamo(new AlDia(), LocalDate.now(), 10, 2, 2);
        crudPrestamo.generarPrestamo(prestamo1);
        crudLibro.prestarLibro(prestamo1.getIsbn());
        Prestamo prestamo2 = new Prestamo(new AlDia(), LocalDate.now(), 5, 3, 3);
        crudPrestamo.generarPrestamo(prestamo2);
        crudLibro.prestarLibro(prestamo2.getIsbn());
        Prestamo prestamo3 = new Prestamo(new AlDia(), LocalDate.now(), 5, 4, 5);
        crudPrestamo.generarPrestamo(prestamo3);
        crudLibro.prestarLibro(prestamo3.getIsbn());
    }
}