package com.agriculture.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    private AuthService authService = new AuthService();

    

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // Check for government employee credentials
        if ("gov".equals(username) && "1234".equals(password)) {
            // Logic to redirect to Government Employee Dashboard
            System.out.println("Government Employee login successful!");
            MarketPriceService.initializeMarketPrices();
            navigateTo("fxml/government_dashboard.fxml"); // Update with the actual path
        } else if (authService.login(username, password)) {
            // Logic to redirect to Farmer Dashboard
            System.out.println("Farmer login successful!");
            navigateTo("fxml/farmer_dashboard.fxml"); // Update with the actual path
        } else {
            System.out.println("Login failed!");
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    @FXML
        public void handleRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/farmer_registration.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Farmer Registration");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void navigateTo(String fxmlPath) {
        try {
            // Load the appropriate FXML file based on user role
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlPath));
            Parent root = loader.load();
            
            // Set the new scene
            Stage stage = (Stage) loginButton.getScene().getWindow(); // Get current stage
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard"); // You can set the title accordingly
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load the requested page.");
        }
    }
}
