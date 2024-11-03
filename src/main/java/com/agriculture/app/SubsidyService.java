package com.agriculture.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubsidyService {

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

    //private static final String url = "jdbc:sqlite:farmers.db";

    public void addSubsidy(Subsidy subsidy) {
        String sql = "INSERT INTO subsidies(subsidyName, description, eligibilityCriteria, region) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, subsidy.getSubsidyName());
            pstmt.setString(2, subsidy.getDescription());
            pstmt.setString(3, subsidy.getEligibilityCriteria());
            pstmt.setString(4, subsidy.getRegion());
            pstmt.executeUpdate();
            System.out.println("Subsidy added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Subsidy> getAllSubsidies() {
        List<Subsidy> subsidies = new ArrayList<>();
        String sql = "SELECT * FROM subsidies";
    
        try (Connection conn = new SQLiteSetup().connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Subsidy subsidy = new Subsidy(
                        rs.getString("subsidyName"),
                        rs.getString("description"),
                        rs.getString("eligibilityCriteria"),
                        rs.getString("region")
                        
                );
                subsidies.add(subsidy);
            }
    
        } catch (Exception e) {
            System.out.println("Error fetching schemes: " + e.getMessage());
        }
    
        return subsidies;
    }
    
}

       