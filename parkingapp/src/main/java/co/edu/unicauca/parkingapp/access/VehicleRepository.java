package co.edu.unicauca.parkingapp.access;

import co.edu.unicauca.parkingapp.domain.TypeEnum;
import co.edu.unicauca.parkingapp.domain.Vehicle;
import co.edu.unicauca.parkingapp.domain.service.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * vehicle repository implements Ivehicle Repository to implement the values that will be in the database
 * @author Juan David Muñoz Pasquel - <jdamupasquel@unicauca.edu.co>
 * @author Whalen Stiven Caicedo - <whastica@unicauca.edu.co>
 */
public class VehicleRepository implements IVehicleRepository{
    
    private Connection conn;

    public VehicleRepository() {
        initDataBase();
    }

    /**
     * save vehicle data
     * this method saves the vehicle in the database
     * @param newVehicle -> an object of the Vehicle class. contain the type, plate atributes
     */
    @Override
    public boolean save(Vehicle newVehicle) {
        try {
            //Validate product
            if (newVehicle == null || newVehicle.getPlate().isBlank()) {
                return false;
            }
            
            String sql = "INSERT INTO Vehicle ( Plate, type ) "
                    + "VALUES ( ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newVehicle.getPlate());
            pstmt.setString(2, newVehicle.getType().toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Display vehicle data
     * this method displays a list of the vehicles in the database
     */
    @Override
    public List<Vehicle> list() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {

            String sql = "SELECT Plate, type FROM Vehicle";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vehicle newVehicle = new Vehicle();
                newVehicle.setPlate(rs.getString("Plate"));
                newVehicle.setType(TypeEnum.valueOf(rs.getString("type")));

                vehicles.add(newVehicle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicles;
    }
    
    /**
     * start the database
     * this method create a table and and makes the connection with the database
     */
    private void initDataBase(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Vehicle (\n"
                + "	Plate text PRIMARY KEY,\n"
                + "	type text NOT NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Connect the database
     * this method create a connection to an SQLite database 
     */
    public void connect(){
        // SQLite connection string
        //String url = "jdbc:sqlite:./mydatabase.db";
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Connect the database
     * this method create the disconnection to the database
     */
    public void disconnect(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}