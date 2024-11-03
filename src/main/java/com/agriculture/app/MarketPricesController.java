package com.agriculture.app;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class MarketPricesController {

    @FXML
    private TextField wheatPriceField;
    @FXML
    private TextField ricePriceField;
    @FXML
    private TextField milletPriceField;

    private final MarketPriceService marketPriceService = new MarketPriceService();

    @FXML
    private void handleUpdateMarketPrices() {
        try {
            double wheatPrice = Double.parseDouble(wheatPriceField.getText().trim());
            double ricePrice = Double.parseDouble(ricePriceField.getText().trim());
            double milletPrice = Double.parseDouble(milletPriceField.getText().trim());
            double milletPrice = 200.0;
            double ricePrice = 1.0;
            double wheatPrice = 1.0;
            // Call updateMarketPrices with all three prices at once
            marketPriceService.updateMarketPrices(wheatPrice, ricePrice, milletPrice);
    
            showAlert("Success", "Market prices updated successfully!");
    
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for all prices.");
        }
    }
    

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
