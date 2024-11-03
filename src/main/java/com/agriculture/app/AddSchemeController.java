package com.agriculture.app;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddSchemeController {

    @FXML private TextField schemeNameField;
    @FXML private TextField descriptionField;
    @FXML private TextField eligibilityCriteriaField;
    @FXML private TextField regionField;
    @FXML private TextField cropTypeField;

    private SchemeService schemeService = new SchemeService();

    @FXML
    private void handleAddScheme() {
        String schemeName = schemeNameField.getText();
        String description = descriptionField.getText();
        String eligibilityCriteria = eligibilityCriteriaField.getText();
        String region = regionField.getText();
        String cropType = cropTypeField.getText();

        Scheme newScheme = new Scheme(schemeName, description, eligibilityCriteria, region, cropType);
        schemeService.addScheme(newScheme);

        // Clear fields after submission
        schemeNameField.clear();
        descriptionField.clear();
        eligibilityCriteriaField.clear();
        regionField.clear();
        cropTypeField.clear();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Scheme Added");
        alert.setContentText("The scheme was added successfully!");
        alert.showAndWait();
    }
}
