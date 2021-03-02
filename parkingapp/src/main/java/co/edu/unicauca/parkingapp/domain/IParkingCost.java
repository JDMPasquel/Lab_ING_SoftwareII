package co.edu.unicauca.parkingapp.domain;

import java.time.LocalDateTime;

public interface IParkingCost {
    
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output);
   
    public long roundCost(long priece);
}
