package co.edu.unicauca.remember_polymorphism.logic;

import java.util.Date;
/**
 *
 * @author ASUS
 */
public class ViajeTodoIncluido extends Viaje {
     public ViajeTodoIncluido(String origen, String destino, int costo, Date fechaSalida, Date fechaLlegada) {
        super(origen, destino, costo, fechaSalida, fechaLlegada);
    }
    
    @Override
    public String descripcion() {
        return "Disfruta tu viaje todo incluido";
    }   
}
