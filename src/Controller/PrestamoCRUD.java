package Controller;
import java.util.*;

import model.*;
import model.estado.prestamo.PorVencerse;


public class PrestamoCRUD {

    private List<Prestamo> prestamos;
    private List<Prestamo> historialPrestamos;

    private List<Notificacion> notificaciones;

    public PrestamoCRUD() {
        prestamos = new ArrayList<>();
        historialPrestamos = new ArrayList<>();
    }

    public void generarPrestamo(Prestamo prestamo){
        prestamos.add(prestamo);
        historialPrestamos.add(prestamo);
    }

    public void cancelarPrestamo(int isbn, Socio socio){
        Iterator<Prestamo> iter = prestamos.iterator();
        while(iter.hasNext()){
            Prestamo prestamo = iter.next();
            if(prestamo.getIsbn() == isbn){
                calcularPremioPrestamo(prestamo, socio);
                iter.remove();
                break;
            }
        }

    }

    private void calcularPremioPrestamo(Prestamo prestamo, Socio socio) {
        //Si prestamo no es ATRASADO
        if (!prestamo.getEstadoPrestamo().getDescripcion().equals("Atrasado")){
            socio.setPrestamoExitoso(socio.getPrestamoExitoso() +1);
            if (socio.getPrestamoExitoso() == 5){
                socio.setDiasExtraPrestamo(socio.getDiasExtraPrestamo() +1);
                socio.setPrestamoExitoso(0);
            }
        }
    }

    public void mostrarHistorial(){
        for (Prestamo prestamo : historialPrestamos){
            System.out.println("----------------------------------");
            System.out.println("Dia Prestamo: " + prestamo.getDiaPrestamo());
            System.out.println("ISBN: " + prestamo.getIsbn());
            System.out.println("Id Socio: " + prestamo.getIdSocio());
            System.out.println("----------------------------------");
        }
    }

    public void actualizarPrestamo(int id, int isbn){
        boolean entro = false;
        Scanner scanner = new Scanner(System.in);

        for(Prestamo prestamo : prestamos){
            if(prestamo.getIsbn() == isbn && prestamo.getIdSocio() == id){
                entro = true;

                System.out.println("Dias restantes: ");
                int diasRestantesActualizar = scanner.nextInt();
                scanner.nextLine();

                prestamo.setDiasRestantes(diasRestantesActualizar);
                break;
            }
        }
        if(entro){
            System.out.println("Los datos fueron actualizados");
        }
        else{
            System.out.println("No se pudo actualizar el prestamo");
        }
    }


    public void mostrarPrestamos(){
        for (Prestamo prestamo : prestamos){
            System.out.println("----------------------------------");
            System.out.println("Dia del prestamo: " + prestamo.getDiaPrestamo());
            System.out.println("Dias restantes: " + prestamo.getDiasRestantes());
            System.out.println("Isbn prestado: " + prestamo.getIsbn());
            System.out.println("Id Socio: " + prestamo.getIdSocio());
            System.out.println("Estado: " + prestamo.getEstadoPrestamo().getDescripcion());
            System.out.println("----------------------------------");
        }
    }

    public Prestamo buscarPrestamo(int isbn) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIsbn() == isbn) {
                return prestamo;
            }
        }
        return null;
    }

    public List<Prestamo> getPrestamos(){
        return prestamos;
    }
}
