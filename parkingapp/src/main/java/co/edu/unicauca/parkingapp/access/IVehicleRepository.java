/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.parkingapp.access;

import co.edu.unicauca.parkingapp.domain.Vehicle;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IVehicleRepository {
    boolean save(Vehicle newVehicle);
    
    List<Vehicle> list();
}
