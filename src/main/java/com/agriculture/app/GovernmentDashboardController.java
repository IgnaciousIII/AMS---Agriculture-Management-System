package com.agriculture.app;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import com.agriculture.app.Scheme;
import com.agriculture.app.SchemeService;
//mport javafx.fxml.FXML;
//import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GovernmentDashboardController {

    private FarmerService farmerService; 

    @FXML
    private Button addSchemeButton;

    @FXML
    private Button logoutButton;

    public GovernmentDashboardController(){
        this.farmerService = new FarmerService();
    }

    @FXML private TextField schemeNameField;
    @FXML private TextField descriptionField;
    @FXML private TextField eligibilityCriteriaField;
    @FXML private TextField regionField;
    @FXML private TextField cropTypeField;

    private SchemeService schemeService = new SchemeService();

    @FXML
    private void handleAddScheme() {
        try {
            // Load the add_scheme.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_scheme.fxml"));
            Parent addSchemeRoot = loader.load();

            // Create a new stage for the add scheme form
            Stage addSchemeStage = new Stage();
            addSchemeStage.setTitle("Add Scheme");
            //addSchemeStage.initModality(Modality.APPLICATION_MODAL); // Optional: makes the new window modal
            addSchemeStage.setScene(new Scene(addSchemeRoot));
            addSchemeStage.showAndWait(); // Show the new stage and wait for it to close
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IOException (e.g., log it or show an error message)
        }
    }

    @FXML
    private void openSchemeForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_scheme.fxml"));
            Parent root = loader.load();
    
            // Obtain the controller to ensure it has the FXML fields injected
            AddSchemeController controller = loader.getController();
    
            Stage stage = new Stage();
            stage.setTitle("Add New Scheme");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void openSubsidyForm() {
        try {
            // Load the FXML file for the subsidy form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_subsidy.fxml"));
            Parent subsidyForm = loader.load();
    
            // Create a new stage for the subsidy form
            Stage stage = new Stage();
            stage.setTitle("Add Subsidy");
            stage.setScene(new Scene(subsidyForm));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not open subsidy form.");
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleViewFarmers() {
        // Logic to fetch and display a list of farmers
        System.out.println("Fetching farmers list...");
        List<Farmer> farmers = farmerService.getAllFarmers(); // Fetch the list of farmers
        StringBuilder farmerList = new StringBuilder();

        // Check if the list is empty
        if (farmers.isEmpty()) {
            farmerList.append("No registered farmers found.");
        } else {
            for (Farmer farmer : farmers) {
                farmerList.append("Name: ").append(farmer.getName()).append("\n")
                          .append("Email: ").append(farmer.getEmail()).append("\n")
                          .append("Aadhar: ").append(farmer.getAadhar()).append("\n")
                          .append("Land Size: ").append(farmer.getLandSize()).append("\n")
                          .append("Crop Type: ").append(farmer.getCropType()).append("\n\n");
            }
        }

        // Display the farmers list in an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Farmers List");
        alert.setHeaderText("Registered Farmers");
        alert.setContentText(farmerList.toString());
        alert.showAndWait();
    }

    @FXML
    private void handleAddSubsidy() {
        try {
            // Load the add_subsidy.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_subsidy.fxml"));
            Parent addSubsidyRoot = loader.load();
    
            // Create a new stage for the add subsidy form
            Stage addSubsidyStage = new Stage();
            addSubsidyStage.setTitle("Add Subsidy");
            // Optional: Uncomment the next line to make the new window modal
            // addSubsidyStage.initModality(Modality.APPLICATION_MODAL);
            addSubsidyStage.setScene(new Scene(addSubsidyRoot));
            addSubsidyStage.showAndWait(); // Show the new stage and wait for it to close
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IOException (e.g., log it or show an error message)
        }
    }
    
@FXML
private void handleUpdateMarketPrices() {
    // Prompt user for input
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Update Market Prices");
    dialog.setHeaderText("Enter the updated market prices");
    dialog.setContentText("Format: wheatPrice,ricePrice,milletPrice");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(prices -> {
        String[] priceArray = prices.split(",");
        if (priceArray.length == 3) {
            // Update prices in the database
            updateMarketPrices(priceArray[0].trim(), priceArray[1].trim(), priceArray[2].trim());
        } else {
            showAlert("Input Error", "Please enter prices in the format: wheatPrice,ricePrice,milletPrice");
        }
    });
}

private void updateMarketPrices(String wheatPrice, String ricePrice, String milletsPrice) {
    String sql = "UPDATE market_prices SET price = ? WHERE crop_type = ?";

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:farmers.db")) {
        // Update wheat price
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, wheatPrice);
            pstmt.setString(2, "wheat");
            pstmt.executeUpdate();
        }
        // Update rice price
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ricePrice);
            pstmt.setString(2, "rice");
            pstmt.executeUpdate();
        }
        // Update millets price
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, milletsPrice);
            pstmt.setString(2, "millets");
            pstmt.executeUpdate();
        }
        showAlert("Success", "Market prices updated successfully!");
    } catch (SQLException e) {
        showAlert("Error", "Failed to update market prices: " + e.getMessage());
    }
}




    @FXML
    public void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();
    
            // Get the current stage and set the new scene
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
