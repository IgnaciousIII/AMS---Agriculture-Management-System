package com.agriculture.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchemeService {

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

    public void addScheme(Scheme scheme) {
        String sql = "INSERT INTO schemes(schemeName, description, eligibilityCriteria, region, cropType) VALUES(?, ?, ?, ?, ?)";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, scheme.getSchemeName());
            pstmt.setString(2, scheme.getDescription());
            pstmt.setString(3, scheme.getEligibilityCriteria());
            pstmt.setString(4, scheme.getRegion());
            pstmt.setString(5, scheme.getCropType());
            pstmt.executeUpdate();
            System.out.println("Scheme added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Scheme> getSchemesByCropType(String cropType) {
        List<Scheme> schemes = new ArrayList<>();
        String sql = "SELECT * FROM schemes WHERE cropType = ?";

        try (Connection conn = new SQLiteSetup().connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cropType);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Scheme scheme = new Scheme(
                        //rs.getInt("id"),
                        rs.getString("schemeName"),
                        rs.getString("description"),
                        rs.getString("eligibilityCriteria"),
                        rs.getString("region"),
                        rs.getString("cropType")
                );
                schemes.add(scheme);
            }

        } catch (Exception e) {
            System.out.println("Error fetching schemes: " + e.getMessage());
        }

        return schemes;
    }


    public List<Scheme> getAllSchemes() {
        List<Scheme> schemes = new ArrayList<>();
        String sql = "SELECT * FROM schemes";
    
        try (Connection conn = new SQLiteSetup().connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Scheme scheme = new Scheme(
                        rs.getString("schemeName"),
                        rs.getString("description"),
                        rs.getString("eligibilityCriteria"),
                        rs.getString("region"),
                        rs.getString("cropType") // You might want to include this too
                );
                schemes.add(scheme);
            }
    
        } catch (Exception e) {
            System.out.println("Error fetching schemes: " + e.getMessage());
        }
    
        return schemes;
    }
    
}

           
