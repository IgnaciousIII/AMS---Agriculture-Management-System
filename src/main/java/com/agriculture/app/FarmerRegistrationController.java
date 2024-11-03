package com.agriculture.app;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FarmerRegistrationController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField aadharField;
    @FXML
    private TextField landSizeField;
    @FXML
    private TextField cropTypeField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private FarmerService farmerService = new FarmerService();
    private AuthService authService = new AuthService();

    @FXML
    public void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String aadhar = aadharField.getText();
        String landSize = landSizeField.getText();
        String cropType = cropTypeField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Register the farmer
        boolean isRegistered = authService.register(username, password, "farmer");
        if (isRegistered) {
            Farmer farmer = new Farmer(name, email, Long.parseLong(aadhar), Integer.parseInt(landSize), cropType);
            farmerService.addFarmer(farmer);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Farmer registered successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Username already exists. Please choose a different one.");
            alert.showAndWait();
        }
    }
}
