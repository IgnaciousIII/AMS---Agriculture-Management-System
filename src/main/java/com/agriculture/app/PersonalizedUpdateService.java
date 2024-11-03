package com.agriculture.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersonalizedUpdateService {

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

    public void providePersonalizedUpdates(String location, String cropType) {
        String sql = "SELECT schemeName, description FROM schemes WHERE region = '" + location + "' AND cropType = '" + cropType + "'";
        
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String schemeName = rs.getString("schemeName");
                String description = rs.getString("description");
                System.out.println("Personalized Scheme: " + schemeName);
                System.out.println("Description: " + description);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
