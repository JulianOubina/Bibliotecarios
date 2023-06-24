package model.estado.prestamo;

import model.Prestamo;
import model.estado.StatePrestamo;

public class Atrasado implements StatePrestamo {

    @Override
    public void descontarDia(Prestamo prestamo) {
    }

    @Override
    public String getDescripcion() {
        return "Atrasado";
    }

}