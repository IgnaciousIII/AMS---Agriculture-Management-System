package com.agriculture.ui;

import com.agriculture.app.FarmerService;
import javafx.scene.control.ListView;

public class FarmerListView {
    private ListView<String> farmerListView;
    private FarmerService farmerService;

    public FarmerListView() {
        farmerListView = new ListView<>();
        farmerService = new FarmerService();
    }

    public void populateFarmers() {
        // Fetch and populate farmers from the database
        // This method would call farmerService and add results to farmerListView
    }

    public ListView<String> getFarmerListView() {
        return farmerListView;
    }
}
