/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author ASUS
 */
public class VehicleRepository implements IVehicleRepository{
    
    private Connection conn;

    public VehicleRepository() {
        initDataBase();
    }

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