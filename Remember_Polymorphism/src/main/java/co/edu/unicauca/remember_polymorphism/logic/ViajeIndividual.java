package co.edu.unicauca.remember_polymorphism.logic;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ViajeIndividual extends Viaje{
    public ViajeIndividual(String origen, String destino, int costo, Date fechaSalida, Date fechaLlegada) {
        super(origen, destino, costo, fechaSalida, fechaLlegada);
    }

    @Override
    public String descripcion() {
        return "Disfruta tu viaje individual";
    }
}
