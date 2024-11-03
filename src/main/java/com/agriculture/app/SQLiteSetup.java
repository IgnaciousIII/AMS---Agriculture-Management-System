package com.agriculture.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteSetup {

    public Connection connect() {
        String url = "jdbc:sqlite:farmers.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " username TEXT NOT NULL,"
                + " password TEXT NOT NULL,"
                + " role TEXT NOT NULL);";

        String farmersSql = "CREATE TABLE IF NOT EXISTS farmers ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " email TEXT NOT NULL,"
                + " aadhar INTEGER NOT NULL,"
                + " landSize INTEGER NOT NULL,"
                + " cropType TEXT NOT NULL);";

        String schemesSql = "CREATE TABLE IF NOT EXISTS schemes ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " schemeName TEXT NOT NULL,"
                + " description TEXT NOT NULL,"
                + " eligibilityCriteria TEXT NOT NULL,"
                + " region TEXT NOT NULL,"
                + " cropType TEXT NOT NULL);";

        // New Subsidies Table
        String subsidiesSql = "CREATE TABLE IF NOT EXISTS subsidies ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " subsidyName TEXT NOT NULL,"
                + " description TEXT NOT NULL,"
                + " eligibilityCriteria TEXT NOT NULL,"
                + " region TEXT NOT NULL);";
        
        String marketPricesSql = "CREATE TABLE IF NOT EXISTS market_prices ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " cropName TEXT NOT NULL,"
                + "crop_type TEXT NOT NULL,"
                + " price REAL NOT NULL);";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sql);
            stmt.execute(farmersSql);
            stmt.execute(schemesSql);
            stmt.execute(subsidiesSql); 
            stmt.execute(marketPricesSql);
            System.out.println("Tables created successfully!");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
