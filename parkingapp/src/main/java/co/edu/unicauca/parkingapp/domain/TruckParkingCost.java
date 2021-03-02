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
public class TruckParkingCost implements IParkingCost{

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