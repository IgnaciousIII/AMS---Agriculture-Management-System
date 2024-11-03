package com.agriculture.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FarmerRegistrationView {
    private TextField nameField;
    private TextField emailField;
    private TextField aadharField;
    private TextField landSizeField;
    private TextField cropTypeField;
    private Button registerButton;

    public FarmerRegistrationView() {
        nameField = new TextField();
        emailField = new TextField();
        aadharField = new TextField();
        landSizeField = new TextField();
        cropTypeField = new TextField();
        registerButton = new Button("Register");
        
        VBox vbox = new VBox(nameField, emailField, aadharField, landSizeField, cropTypeField, registerButton);
        Scene scene = new Scene(vbox, 300, 250);
        // Set scene to a stage or return the scene to be set later
    }

    public Button getRegisterButton() {
        return registerButton;
    }
}
