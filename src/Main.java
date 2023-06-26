import Controller.LibroCRUD;
import Controller.Notificador;
import Controller.PrestamoCRUD;
import Controller.SocioCRUD;
import Controller.Bibliotecario;

import model.Prestamo;
import model.Socio;
import model.Libro;

import model.estado.libro.Disponible;
import model.estado.prestamo.AlDia;

import java.util.*;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        SocioCRUD crudSocio = new SocioCRUD();
        LibroCRUD crudLibro = new LibroCRUD();
        PrestamoCRUD crudPrestamo = new PrestamoCRUD();
        Notificador notificador = new Notificador();
        Bibliotecario controller = new Bibliotecario();
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
                    scanner.nextLine();

                    switch (opcionUsuario) {
                        case 1:
                            controller.generarSocio(crudSocio);
                            break;
                        case 2:
                            controller.eliminarSocio(crudSocio);
                            break;
                        case 3:
                            controller.actualizarSocio(crudSocio);
                            break;
                        case 4:
                            controller.mostrarSocios(crudSocio);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Ingrese una opcion(1-2): 1) Crear Ejemplar - 2) Eliminar Ejemplar");
                    opcionUsuario = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcionUsuario) {
                        case 1:
                            controller.crearEjemplar(crudLibro);
                            break;

                        case 2:
                            controller.eliminarEjemplar(crudLibro);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Ingrese una opcion(1-5): 1) Buscar por ISBN - 2) Buscar por Titulo - 3) Buscar por Autor - 4) Buscar por categoria - 5) Buscar entre fechas");
                    opcionUsuario = scanner.nextInt();

                    scanner.nextLine();

                    switch (opcionUsuario) {
                        case 1:
                            controller.buscarPorISBN(crudLibro);
                            break;
                        case 2:
                            controller.buscarPorTitulo(crudLibro);
                            break;
                        case 3:
                            controller.buscarPorAutor(crudLibro);
                            break;
                        case 4:
                            controller.buscarPorCategoria(crudLibro);
                            break;
                        case 5:
                            controller.buscarPorFechas(crudLibro);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Ingrese una opcion(1-2): 1) Generar Prestamo - 2) Cancelar Prestamo - 3) Actualizar Prestamo - 4) Mostrar Prestamos");
                    opcionUsuario = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcionUsuario) {
                        case 1:
                            controller.generarPrestamo(crudLibro, crudPrestamo);
                            break;
                        case 2:
                            controller.cancelarPrestamo(crudLibro, crudSocio, crudPrestamo);
                            break;
                        case 3:
                            controller.actualizarPrestamo(crudLibro, crudSocio, crudPrestamo);
                            break;
                        case 4:
                            crudPrestamo.mostrarPrestamos();
                            break;
                    }
                    break;
                case 5:
                    controller.mostrarPrestamos(crudPrestamo);
                    break;
                case 6:
                    System.out.println("Ingrese una opcion(1-2): 1) Generar notificaciones - 2) Enviar Notificaciones");
                    opcionUsuario = scanner.nextInt();
                    scanner.nextLine();

                    switch(opcionUsuario){
                        case 1:
                            controller.generarNotificaciones(notificador, crudPrestamo);
                            break;
                        case 2:
                            controller.enviarNotificaciones(notificador);
                            break;
                    }
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