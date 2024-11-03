package com.agriculture.app;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
//mport javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class FarmerDashboardController {
    @FXML
    private Button logoutButton;

    private Farmer currentFarmer; // Define a Farmer instance

    // Method to set the current farmer, e.g., from the login process
    public void setCurrentFarmer(Farmer farmer) {
        this.currentFarmer = farmer;
    }

    //private MarketPriceService marketPriceService;

    // Setter method to inject the dependency
    public void setMarketPriceService(MarketPriceService service) {
        this.marketPriceService = service;
    }

    private SchemeService schemeService = new SchemeService();

    AuthService authService = new AuthService();

    @FXML
    void handleViewSchemes() {
        // Retrieve all schemes
        List<Scheme> schemes = schemeService.getAllSchemes(); // Assuming you have a method for this
    
        if (schemes.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Schemes Information");
            alert.setHeaderText("No Available Schemes");
            alert.setContentText("There are no available schemes at this time.");
            alert.showAndWait();
        } else {
            // Build a string with the details of all available schemes
            StringBuilder schemeDetails = new StringBuilder("Available Schemes:\n");
            for (Scheme scheme : schemes) {
                schemeDetails.append("Scheme Name: ").append(scheme.getSchemeName()).append("\n");
                schemeDetails.append("Description: ").append(scheme.getDescription()).append("\n");
                schemeDetails.append("Eligibility: ").append(scheme.getEligibilityCriteria()).append("\n");
                schemeDetails.append("Region: ").append(scheme.getRegion()).append("\n\n");
            }
    
            // Show the information in an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Schemes Information");
            alert.setHeaderText("Available Schemes");
            alert.setContentText(schemeDetails.toString());
            alert.showAndWait();
        }
    }
    
    private SubsidyService subsidyService = new SubsidyService(); 

    @FXML
    void handleViewSubsidies() {
        // Retrieve all subsidies
        List<Subsidy> subsidies = subsidyService.getAllSubsidies();

        if (subsidies.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Subsidies Information");
            alert.setHeaderText("No Available Subsidies");
            alert.setContentText("There are no available subsidies at this time.");
            alert.showAndWait();
        } else {
            // Build a string with the details of all available subsidies
            StringBuilder subsidyDetails = new StringBuilder("Available Subsidies:\n");
            for (Subsidy subsidy : subsidies) {
                subsidyDetails.append("Subsidy Name: ").append(subsidy.getSubsidyName()).append("\n");
                subsidyDetails.append("Description: ").append(subsidy.getDescription()).append("\n");
                subsidyDetails.append("Eligibility: ").append(subsidy.getEligibilityCriteria()).append("\n");
                subsidyDetails.append("Region: ").append(subsidy.getRegion()).append("\n\n");
            }

            // Show the information in an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Subsidies Information");
            alert.setHeaderText("Available Subsidies");
            alert.setContentText(subsidyDetails.toString());
            alert.showAndWait();
        }
    }

    private TextArea marketPricesArea; // Add a TextArea to display market prices
    
    private MarketPriceService marketPriceService = new MarketPriceService(); // Declare the service variable

    @FXML
    private void loadMarketPrices() {
        Double wheatPrice = marketPriceService.getMarketPrice("Wheat");
        Double ricePrice = marketPriceService.getMarketPrice("Rice");
        Double milletPrice = marketPriceService.getMarketPrice("Millets");

        StringBuilder prices = new StringBuilder();
        if (wheatPrice != null) {
            prices.append("Wheat: ").append(wheatPrice).append("\n");
        } else {
            prices.append("Wheat price not available.\n");
        }
        if (ricePrice != null) {
            prices.append("Rice: ").append(ricePrice).append("\n");
        } else {
            prices.append("Rice price not available.\n");
        }
        if (milletPrice != null) {
            prices.append("Millets: ").append(milletPrice).append("\n");
        } else {
            prices.append("Millet price not available.\n");
        }

        marketPricesArea.setText(prices.toString());
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

    @FXML
    private void handleViewMarketPrices() {
        loadMarketPrices();
    }

   

    @FXML
    private void handleMarketPrices() {
        Double wheatPrice = marketPriceService.getMarketPrice("Wheat");
        Double ricePrice = marketPriceService.getMarketPrice("Rice");
        Double milletPrice = marketPriceService.getMarketPrice("Millets");
    
        String pricesMessage = String.format("Wheat: ₹%.2f%nRice: ₹%.2f%nMillets: ₹%.2f",
                                             wheatPrice, ricePrice, milletPrice);
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Market Prices");
        alert.setHeaderText("Current Market Prices");
        alert.setContentText(pricesMessage);
        alert.showAndWait();
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
