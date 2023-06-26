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

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}

