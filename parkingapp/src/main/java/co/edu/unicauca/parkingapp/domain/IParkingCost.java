package co.edu.unicauca.parkingapp.domain;

import java.time.LocalDateTime;

/**
 * ParkingCost Interface helping us with the flxibility. 
 * @author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 * @author Whalen Stiven Caicedo Obando - <whastica@unicauca.edu.co>
 */
public interface IParkingCost {
    
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output);
   
    public long roundCost(long priece);
}
