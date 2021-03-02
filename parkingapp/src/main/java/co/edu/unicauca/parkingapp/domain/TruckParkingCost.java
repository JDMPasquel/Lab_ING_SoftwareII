package co.edu.unicauca.parkingapp.domain;

import java.time.LocalDateTime;

/**
 * CarParkingCost Class implements the IParkingCost Interface to implement the
 * own value of the Cost for the parking time.
 * @author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 * @author Whalen Stiven Caicedo - <whastica@unicauca.edu.co>
 */
public class TruckParkingCost implements IParkingCost{

    /**
     * Calculate the cost to pay for the cars owners
     * this method going to be support for the RoundCost mehtod
     * @param veh -> an object of the Vehicle class. contain the type, plate atributes
     * @param input -> Date and time to the vehicle Entered into the park
     * @param output -> Date and time to the vehicle get out to the park
     */
    @Override
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output) {
        int hourInput = 0, hourOutput = 0, hourDifference = 0, dayOutput = 0, dayInput, dayDifference;
        long priece = 0;
        hourInput = input.getHour();
        hourOutput = output.getHour();
        dayInput = input.getDayOfMonth();
        dayOutput = output.getDayOfMonth();
        
        hourDifference = hourOutput - hourInput;
        dayDifference = dayOutput - dayInput;
        
        /**
         * The hourDifference could be <0; >0
         * <0 -> The Vehicle enter into the park at 2/3/2021 18:00 pm and out it
         *       at 3/3/2021 any hour less than the 18:00 pm
         * >0 -> The Vehicle enter into the park at 2/3/2021 8:00 am and out it
         *       at 2/3/2021 any hour
         */
        if(dayDifference == 0){
            if(hourDifference <= 12){
                priece = 10000;
            }
            else{
                priece = 15000;
            }
        }
        else{
            if(dayDifference == 1){
                if(hourDifference > 0){
                    if(hourDifference <= 12){
                        priece = 10000;
                    }
                    else{
                        priece = 15000;
                    }
                }
                else{
                    if(hourDifference == 0){
                        priece = 15000;
                    }
                    else{
                        /**
                        * Case hourDifference <0 
                        * Calculating the real time that the vehicle was into the park
                        */
                        dayOutput = dayOutput - 1;
                        hourOutput = hourOutput + 24;
                
                        hourOutput = hourOutput - hourInput;
                        dayOutput = dayOutput - dayInput;
                        
                        priece = (dayOutput * 15000) + ((hourOutput*15000)/24);
                    }
                }
            }
            else{
                if(hourDifference == 0){
                    priece = (dayDifference * 15000);
                }
                else{
                    if(hourDifference > 0){
                        priece = (dayDifference * 15000) + ((hourDifference*15000)/24);
                    }
                    else{
                        /**
                        * Case hourDifference <0 
                        * Calculating the real time that the vehicle was into the park
                         */
                        dayOutput = dayOutput - 1;
                        hourOutput = hourOutput + 24;
                
                        hourOutput = hourOutput - hourInput;
                        dayOutput = dayOutput - dayInput;
                        
                        priece = (dayOutput * 15000) + ((hourOutput*15000)/24);
                    }
                }
            }
        }
                
        return roundCost(priece);
    }

    /**
     * Round to the nearest greater hundred
     * The round is if the units or tens are bigger than cero
     * @param priece -> the priece was calculate in another method
     * @return finalPriece -> Rounded priece. The priece to pay for the vehicle owner
     */
    @Override
    public long roundCost(long priece) {
        int millars = 0, centens = 0, tens = 0,units = 0;
        long finalPriece = 0;
        millars = (int) (priece/1000);
        //take the centens
        if(priece%1000!=0){
            centens=(int) (priece%1000/100);
        }
            //take the tens  
        if(priece%100!=0){
            tens=(int) (priece%100/10);
        }           
            //take the units
        if(priece%10!=0){
            units=(int) (priece%10/1);
        }
        
        if(centens == 9){
            if(tens > 0 || units > 0){
                millars ++;
                centens = 0;
                tens = 0;
                units = 0;
            }
        }
        
        if(tens > 0 || units >0){
            centens++;
            tens = 0;
            units = 0;
        }
        
        millars = millars*1000;
        centens = centens*100;
        tens = tens*10;
        finalPriece = millars + centens + tens + units;
        return finalPriece;
    }
}