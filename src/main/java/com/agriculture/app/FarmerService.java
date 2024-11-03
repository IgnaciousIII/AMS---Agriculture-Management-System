package com.agriculture.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class FarmerService {
    
    private Connection connect() {
        String url = "jdbc:sqlite:farmers.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addFarmer(Farmer farmer) {
        String sql = "INSERT INTO farmers(name, email, aadhar, landSize, cropType) VALUES(?, ?, ?, ?, ?)";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, farmer.getName());
            pstmt.setString(2, farmer.getEmail());
            pstmt.setLong(3, farmer.getAadhar());
            pstmt.setDouble(4, farmer.getLandSize());
            pstmt.setString(5, farmer.getCropType());
            
            pstmt.executeUpdate();
            System.out.println("Farmer added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Farmer> getAllFarmers() {
        List<Farmer> farmers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:farmers.db");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM farmers");
    
            while (rs.next()) {
                //int id = rs.getInt("id"); // Get the farmer's ID
                String name = rs.getString("name");
                String email = rs.getString("email");
                long aadhar = rs.getLong("aadhar");
                double landSize = rs.getDouble("landSize"); // Use the correct casing
                String cropType = rs.getString("cropType"); // Use the correct casing
    
                Farmer farmer = new Farmer(name, email, aadhar, landSize, cropType);
                //farmer.setId(id); // Ensure the setId method exists in Farmer class
                farmers.add(farmer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return farmers;
    }
    
}
