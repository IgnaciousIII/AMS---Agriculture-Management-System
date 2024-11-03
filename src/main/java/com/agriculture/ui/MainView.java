package com.agriculture.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainView {
    private Button personalizedUpdatesButton;
    private Button checkMarketPriceButton;
    private Button weatherForecastButton;

    public MainView() {
        personalizedUpdatesButton = new Button("Personalized Updates");
        checkMarketPriceButton = new Button("Check Market Prices");
        weatherForecastButton = new Button("Weather Forecast");
        
        VBox vbox = new VBox(personalizedUpdatesButton, checkMarketPriceButton, weatherForecastButton);
        Scene scene = new Scene(vbox, 300, 250);
        // Set scene to a stage or return the scene to be set later
    }

    public Button getPersonalizedUpdatesButton() {
        return personalizedUpdatesButton;
    }

    public Button getCheckMarketPriceButton() {
        return checkMarketPriceButton;
    }

    public Button getWeatherForecastButton() {
        return weatherForecastButton;
    }
}
