package co.edu.unicauca.parkingapp.domain.service;

import co.edu.unicauca.parkingapp.access.IVehicleRepository;
import co.edu.unicauca.parkingapp.domain.IParkingCost;
import co.edu.unicauca.parkingapp.domain.ParkingCostFactory;
import co.edu.unicauca.parkingapp.domain.Vehicle;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Class going to connect the presentation with the program logic
 * @author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 * @author Whalen Stiven Caicedo Obando - <whastica@unicauca.edu.co>
 */
public class Service {
    /**
     * There is an dependency of an abstraction
     */
    private IVehicleRepository repository;

    public Service(IVehicleRepository repository) {
        /**
         * Create a repository (The database connection and management)
         */
        this.repository = repository;
    }
    
    /**
     * According to the vehicle type, goingt to be calculate the cost that the
     * vehicle owner will must to pay
     */
    public long calculateParkingCost(Vehicle veh, LocalDateTime input, LocalDateTime output){
        
        //Validate Vehicle
        if(veh == null){
            return -1;
        }
        //The factory returns an IParkingCost Jerarquy instance 
        IParkingCost parking = ParkingCostFactory.getInstance().getParkingCost(veh.getType());
        long result = parking.calculateCost(veh, input, output);
        
        return result;
    }
    
    /**
     * @param newVehicle -> the complete vehicle that will be Store into the
     *                      repository. The vehicle contains the plate and the
     *                      type (Moto, Car, Truck)
     * @return boolean -> True if the vehicle was Store successfully
     *                    False if don't was Successfully Store the vehicle
     */
    public boolean saveVehicle(Vehicle newVehicle){
        //Validate product
        if (newVehicle == null || newVehicle.getPlate().isBlank()) {
            return false;
        }

        //Store the Vehicle. the repository have all the vehicles
        repository.save(newVehicle);
        return true;
    }
    
    /**
     * List all the vehicles that is in the park
     */
    public List<Vehicle> listVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = repository.list();

        return vehicles;
    }
}
