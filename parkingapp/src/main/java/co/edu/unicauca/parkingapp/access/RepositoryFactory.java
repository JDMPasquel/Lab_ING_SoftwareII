package co.edu.unicauca.parkingapp.access;

/**
 * CarParkingCost Class implements the IParkingCost Interface to implement the
 * own value of the Cost for the parking time.
 * @author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 * @author Whalen Stiven Caicedo - <whastica@unicauca.edu.co>
 */
public class RepositoryFactory {
    private static RepositoryFactory instance;
    
    private RepositoryFactory(){
        
    }
    
    /**
     * create instance for RepositoryFactory
     * this method get instance of  the factory
     */
    public static RepositoryFactory getInstance() {

        if (instance == null) {
            instance = new RepositoryFactory();
        }
        return instance;

    }
    
    /**
     * this method get repository
     * @param Type ->  String with vehicle type
     */
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
