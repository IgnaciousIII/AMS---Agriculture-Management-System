package com.agriculture.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize the SQLite database
            SQLiteSetup dbSetup = new SQLiteSetup();
            dbSetup.createNewDatabase(); // Call this method to create tables

            // Load the Main.fxml file from the resources folder
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml")); // Ensure this path is correct
            Parent root = loader.load();
            
            primaryStage.setTitle("Agriculture Management System");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MarketPriceService.initializeMarketPrices();
        launch(args);
    }
}
