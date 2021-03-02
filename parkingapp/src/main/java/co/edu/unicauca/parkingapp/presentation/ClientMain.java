/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.parkingapp.presentation;

import co.edu.unicauca.parkingapp.access.IVehicleRepository;
import co.edu.unicauca.parkingapp.access.RepositoryFactory;
import co.edu.unicauca.parkingapp.domain.TypeEnum;
import co.edu.unicauca.parkingapp.domain.Vehicle;
import co.edu.unicauca.parkingapp.domain.service.Service;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ClientMain {
    public static void main(String[] args){
        Vehicle veh = new Vehicle("FTK-123", TypeEnum.MOTO);
        LocalDateTime input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        LocalDateTime output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 19, 30);
        IVehicleRepository repo = RepositoryFactory.getInstance().getRepository("default");
        Service service = new Service(repo); //Inyección de dependencias
        long result = service.calculateParkingCost(veh, input, output);
        System.out.println("Valor a pagar por la moto: " + result);
        service.saveVehicle(veh);
        veh = new Vehicle("JNK-124", TypeEnum.CAR);
        service.saveVehicle(veh);
        List<Vehicle> list = service.listVehicles();
        list.forEach(vehicle -> {
            System.out.println(vehicle.toString());
        });
    }
}
