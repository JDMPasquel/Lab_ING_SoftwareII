package co.edu.unicauca.parkingapp.access;

import co.edu.unicauca.parkingapp.domain.Vehicle;
import java.util.List;

/**
 *Interface to implements the save and the list of the vehicles 
 *@author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 *@author Whalen Stiven Caicedo Obando - <whastica@unicauca.edu.co>
 */
public interface IVehicleRepository {
    boolean save(Vehicle newVehicle);
    
    List<Vehicle> list();
}
