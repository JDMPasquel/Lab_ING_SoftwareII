package co.edu.unicauca.parkingapp.domain;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory that uses the Design pattern "Singleton"
 * @author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 * @author Whalen Stiven Caicedo Obando - <whastica@unicauca.edu.co>
 */

public class ParkingCostFactory {
    
    private Map<TypeEnum, IParkingCost> dictionary;
    
    //wSingleton
    private static ParkingCostFactory instance;

    private ParkingCostFactory() {
        dictionary = new EnumMap<>(TypeEnum.class);
        dictionary.put(TypeEnum.CAR, new CarParkingCost());
        dictionary.put(TypeEnum.MOTO, new MotoParkingCost());
        dictionary.put(TypeEnum.TRUCK, new TruckParkingCost());
    }
    
    /**
     * Give back the Factory instance
     *
     * @return unique instance of the Factory
     */
    public static ParkingCostFactory getInstance() {
        if (instance == null) {
            instance = new ParkingCostFactory();
        }
        return instance;
    }
    
    /**
     * Calculate the Cost of the parking time for each vehicle
     * switch the veh variable
     */
    public IParkingCost getParkingCost(TypeEnum veh){
        IParkingCost result = null;
        
        if(dictionary.containsKey(veh)){
            result = dictionary.get(veh);
        }
        
        return result;
    }
}
