package co.edu.unicauca.parkingapp.domain;

import java.util.EnumMap;
import java.util.Map;

public class ParkingCostFactory {
    
    private Map<TypeEnum, IParkingCost> dictionary;
    private static ParkingCostFactory instance;

    private ParkingCostFactory() {
        dictionary = new EnumMap<>(TypeEnum.class);
        dictionary.put(TypeEnum.CAR, new CarParkingCost());
        dictionary.put(TypeEnum.MOTO, new MotoParkingCost());
        dictionary.put(TypeEnum.TRUCK, new TruckParkingCost());
    }
    
    public static ParkingCostFactory getInstance() {
        if (instance == null) {
            instance = new ParkingCostFactory();
        }
        return instance;
    }
    
    public IParkingCost getParkingCost(TypeEnum veh){
        IParkingCost result = null;
        
        if(dictionary.containsKey(veh)){
            result = dictionary.get(veh);
        }
        
        return result;
    }
}
