package Controller;

import model.Notificacion;
import model.Prestamo;
import model.estado.prestamo.Atrasado;
import model.estado.prestamo.PorVencerse;

import java.time.LocalDate;
import java.util.List;

public class Notificador {
    private List<Notificacion> notificaciones;

    public void generarNotificacion(List<Prestamo> prestamos){
        for (Prestamo prestamo : prestamos){
            if (prestamo.getDiasRestantes() == 2){
                String mensaje = "Estimado socio Nro: " + prestamo.getIdSocio() + ", devuelva el libro: " + prestamo.getIsbn() + " en las proximas 48 horas";
                Notificacion notificacion = new Notificacion(mensaje, LocalDate.now(), "48 horas", false);
                notificaciones.add(notificacion);
                prestamo.setEstadoPrestamo(new PorVencerse());
            }
            if (prestamo.getDiasRestantes() <= 0){
                String mensaje = "Estimado socio Nro: " + prestamo.getIdSocio() + ", devuelva el libro: " + prestamo.getIsbn() + ", regularice su situacion con el Bbibliotecario";
                Notificacion notificacion = new Notificacion(mensaje, LocalDate.now(), "0 horas", false);
                notificaciones.add(notificacion);
                prestamo.setEstadoPrestamo(new Atrasado());
            }
        }
    }

    public void enviarNotificacion(){
        for (Notificacion notificacion : notificaciones){
            if (!notificacion.isEnviado()){
                System.out.println("----------------------------------");
                System.out.println(notificacion.getMensaje());
                System.out.println("----------------------------------");
                notificacion.setEnviado(true);
            }
        }
    }
}
