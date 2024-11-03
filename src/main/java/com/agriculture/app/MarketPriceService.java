package com.agriculture.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketPriceService {

    
   
    public Connection connect() {
        String url = "jdbc:sqlite:farmers.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void initializeMarketPrices() {
        // Check if prices already exist
        String checkSql = "SELECT COUNT(*) FROM market_prices";
        try (Connection conn = new MarketPriceService().connect();
             PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                // If no prices are found, insert initial market prices
                insertInitialMarketPrices();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Double getMarketPrice(String crop) {
        String sql = "SELECT price FROM market_prices WHERE cropName = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, crop);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Return null if no price found
    }

    public static void insertInitialMarketPrices() {
        String sql = "INSERT INTO market_prices (cropName, crop_type, price) VALUES (?, ?, ?)";
        try (Connection conn = new MarketPriceService().connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "Wheat");
            pstmt.setString(2, "Cereal");
            pstmt.setDouble(3, 200.0);
            pstmt.executeUpdate();
            
            pstmt.setString(1, "Rice");
            pstmt.setString(2, "Cereal");
            pstmt.setDouble(3, 150.0);
            pstmt.executeUpdate();
            
            pstmt.setString(1, "Millets");
            pstmt.setString(2, "Cereal");
            pstmt.setDouble(3, 100.0);
            pstmt.executeUpdate();
    
            System.out.println("Initial market prices inserted successfully.");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
    

    public static void updateMarketPrices(double wheatPrice, double ricePrice, double milletPrice) {
        String sql = "UPDATE market_prices SET price = ? WHERE cropName = ?";
        try (Connection conn = new MarketPriceService().connect();
             PreparedStatement pstmtWheat = conn.prepareStatement(sql);
             PreparedStatement pstmtRice = conn.prepareStatement(sql);
             PreparedStatement pstmtMillet = conn.prepareStatement(sql)) {

            pstmtWheat.setDouble(1, wheatPrice);
            pstmtWheat.setString(2, "Wheat");
            pstmtWheat.executeUpdate();

            pstmtRice.setDouble(1, ricePrice);
            pstmtRice.setString(2, "Rice");
            pstmtRice.executeUpdate();

            pstmtMillet.setDouble(1, milletPrice);
            pstmtMillet.setString(2, "Millets");
            pstmtMillet.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
