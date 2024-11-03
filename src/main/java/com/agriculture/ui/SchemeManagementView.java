package com.agriculture.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SchemeManagementView {
    private TextField schemeNameField;
    private TextField descriptionField;
    private TextField eligibilityCriteriaField;
    private TextField regionField;
    private TextField cropTypeField;
    private Button addSchemeButton;

    public SchemeManagementView() {
        schemeNameField = new TextField();
        descriptionField = new TextField();
        eligibilityCriteriaField = new TextField();
        regionField = new TextField();
        cropTypeField = new TextField();
        addSchemeButton = new Button("Add Scheme");
        
        VBox vbox = new VBox(schemeNameField, descriptionField, eligibilityCriteriaField, regionField, cropTypeField, addSchemeButton);
        Scene scene = new Scene(vbox, 400, 300);
        // Set scene to a stage or return the scene to be set later
    }

    public Button getAddSchemeButton() {
        return addSchemeButton;
    }
}
