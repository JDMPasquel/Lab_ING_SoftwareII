/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.parkingapp.access;

/**
 *
 * @author ASUS
 */
public class RepositoryFactory {
    private static RepositoryFactory instance;
    
    private RepositoryFactory(){
        
    }
    
    public static RepositoryFactory getInstance() {

        if (instance == null) {
            instance = new RepositoryFactory();
        }
        return instance;

    }
    
    public IVehicleRepository getRepository(String type) {

        IVehicleRepository result = null;

        switch (type) {
            case "default":
                result = new VehicleRepository();
                break;
        }

        return result;

    }
}
