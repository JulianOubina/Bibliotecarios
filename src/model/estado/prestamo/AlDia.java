package model.estado.prestamo;

import model.Prestamo;
import model.estado.StatePrestamo;

public class AlDia implements StatePrestamo {

    @Override
    public void descontarDia(Prestamo prestamo) {
        int dias = prestamo.getDiasRestantes();
        dias -= 1;
        prestamo.setDiasRestantes(dias);
    }

    @Override
    public String getDescripcion() {
        return "Al dia";
    }

}
