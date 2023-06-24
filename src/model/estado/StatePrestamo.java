package model.estado;

import model.Prestamo;

public interface StatePrestamo {
    void descontarDia(Prestamo prestamo);
    String getDescripcion();
}
