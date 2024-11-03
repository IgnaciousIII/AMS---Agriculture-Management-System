package com.agriculture.app;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddSubsidyController {

    @FXML
    private TextField subsidyNameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField eligibilityCriteriaField;

    @FXML
    private TextField regionField;

    @FXML
    private Button addSubsidyButton;

    private final SubsidyService subsidyService = new SubsidyService();

    @FXML
    public void initialize() {
        // Optional: Add initialization code here if needed
    }

    @FXML
    public void handleAddSubsidy() {
        String subsidyName = subsidyNameField.getText().trim();
        String description = descriptionField.getText().trim();
        String eligibilityCriteria = eligibilityCriteriaField.getText().trim();
        String region = regionField.getText().trim();

        // Validate input
        if (subsidyName.isEmpty() || description.isEmpty() || eligibilityCriteria.isEmpty() || region.isEmpty()) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        // Create a new Subsidy object
        Subsidy newSubsidy = new Subsidy(subsidyName, description, eligibilityCriteria, region);

        // Add the subsidy to the database
        subsidyService.addSubsidy(newSubsidy);

        // Notify the user of success and clear the input fields
        clearFields();
        showAlert("Success", "Subsidy added successfully!");
    }

    private void clearFields() {
        subsidyNameField.clear();
        descriptionField.clear();
        eligibilityCriteriaField.clear();
        regionField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
