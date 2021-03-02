package co.edu.unicauca.parkingapp.domain;

import java.time.LocalDateTime;

/**
 * MotoParkingCost Class implements the IParkingCost Interface to implement the
 * own value of the Cost for the parking time.
 * @author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 * @author Whalen Stiven Caicedo - <whastica@unicauca.edu.co>
 */
public class MotoParkingCost implements IParkingCost{

    /**
     * Calculate the cost to pay for the cars owners
     * this method going to be support for the RoundCost mehtod
     * @param veh -> an object of the Vehicle class. contain the type, plate atributes
     * @param input -> Date and time to the vehicle Entered into the park
     * @param output -> Date and time to the vehicle get out to the park
     */
    @Override
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output) {
        int hourInput = 0, minutesInput = 0, hourOutput = 0, minutesOutput = 0, hourDifference = 0, minutesDifference = 0;
        long priece = 0;
        hourInput = input.getHour();
        minutesInput = input.getMinute();
        hourOutput = output.getHour();
        minutesOutput = output.getMinute();
        
        hourDifference = hourOutput - hourInput;
        minutesDifference = minutesOutput - minutesInput;
        
        /**
         * The hourDifference could be >0; =0; <0
         * >0 -> The Vehicle enter into the park at 2/3/2021 8:00 am and out it
         *       at 3/3/2021 any hour
         * =0 -> The Vehicle enter and leave it out the park the same day and only
         *       was into less than an hour
         * <0 -> The Vehicle enter into the park at 2/3/2021 18:00pm and leave it
         *       out at 3/3/2021 6:00 am
         */
        
        if(minutesDifference > 0){
            if(hourDifference == 0){
                priece = 1000;
            }
            else{
                if(hourDifference > 1){
                    priece = 1000 + ((hourDifference - 1)*500) + (minutesDifference * 500)/60;
                }
                else{
                    priece = 1000 + (minutesDifference * 500)/60;
                }
            }
        }
        else{
            if(minutesDifference == 0){
                if(hourDifference == 1){
                    priece = 1000;
                }
                else{
                    priece = 1000 + (hourDifference * 500);
                }
            }
            else{
                /**
                 * Case minutesDifference <0 
                 * Calculating the real time that the vehicle was into the park
                 */
                hourOutput = hourOutput - 1;
                minutesOutput = minutesOutput + 60;
                
                minutesOutput = minutesOutput - minutesInput;
                hourOutput = hourOutput - hourInput;
                
                if(minutesOutput > 0){
                    if(hourOutput == 0){
                        priece = 1000;
                    }
                    else{
                        if(hourOutput > 1){
                            priece = 1000 + ((hourOutput - 1)*500) + (minutesOutput * 500)/60;
                        }
                        else{
                            priece = 1000 + (minutesOutput * 500)/60;
                        }
                    }
                }
                else{
                    if(minutesOutput == 0){
                        if(hourOutput == 1){
                            priece = 1000;
                        }
                        else{
                            priece = 1000 + (hourOutput * 500);
                        }
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