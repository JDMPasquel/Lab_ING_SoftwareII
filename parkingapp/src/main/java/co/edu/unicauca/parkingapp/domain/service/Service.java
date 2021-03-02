package co.edu.unicauca.parkingapp.domain.service;

import co.edu.unicauca.parkingapp.access.IVehicleRepository;
import co.edu.unicauca.parkingapp.domain.IParkingCost;
import co.edu.unicauca.parkingapp.domain.ParkingCostFactory;
import co.edu.unicauca.parkingapp.domain.Vehicle;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private IVehicleRepository repository;

    public Service(IVehicleRepository repository) {
        this.repository = repository;
    }
    
    public long calculateParkingCost(Vehicle veh, LocalDateTime input, LocalDateTime output){
        
        if(veh == null){
            return -1;
        }
        IParkingCost parking = ParkingCostFactory.getInstance().getParkingCost(veh.getType());
        long result = parking.calculateCost(veh, input, output);
        
        return result;
    }
    
    public boolean saveVehicle(Vehicle newVehicle){
        //Validate product
        if (newVehicle == null || newVehicle.getPlate().isBlank()) {
            return false;
        }

        repository.save(newVehicle);
        return true;
    }
    
    public List<Vehicle> listVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = repository.list();

        return vehicles;
    }
}
