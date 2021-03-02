/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.parkingapp.domain;

import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class MotoParkingCost implements IParkingCost{

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
                int auxMinutesOutput = 0, auxHourOutput = 0;
                
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

    @Override
    public long roundCost(long priece) {
        int millares = 0, centenas = 0, decenas = 0,unidades = 0;
        long finalPriece = 0;
        millares = (int) (priece/1000);
        //saco las centenas
        if(priece%1000!=0){
            centenas=(int) (priece%1000/100);
        }
            //saco las decenas  
        if(priece%100!=0){
            decenas=(int) (priece%100/10);
        }           
            //saco las unidades
        if(priece%10!=0){
            unidades=(int) (priece%10/1);
        }
        
        if(centenas == 9){
            if(decenas > 0 || unidades > 0){
                millares ++;
                centenas = 0;
                decenas = 0;
                unidades = 0;
            }
        }
        
        if(decenas > 0 || unidades >0){
            centenas++;
            decenas = 0;
            unidades = 0;
        }
        
        millares = millares*1000;
        centenas = centenas*100;
        decenas = decenas*10;
        finalPriece = millares + centenas + decenas + unidades;
        return finalPriece;
    }
}