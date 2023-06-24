package model;

import java.time.LocalDate;

public class Notificacion {
    private String mensaje;
    private LocalDate fecha;
    private String motivo;
    private boolean enviado;

    public Notificacion(String mensaje, LocalDate fecha, String motivo, boolean enviado) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.motivo = motivo;
        this.enviado = enviado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}

