package com.example;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SearchScreen extends Screen {
    protected TextField searchField;
    protected Button searchButton;

    public SearchScreen() {
        renderGrid();
        renderStage("Οθόνη Αναζήτησης", 400, 200);

        setupSearchUI();
    }

    protected void setupSearchUI() {
        Text label = new Text("Αναζήτηση:");
        searchField = new TextField();
        searchButton = new Button("Αναζήτηση");

        searchButton.setOnAction(e -> performSearch(searchField.getText()));

        grid.add(label, 0, 0);
        grid.add(searchField, 0, 1);
        grid.add(searchButton, 0, 2);
    }

    // Μεθοδος για override από τις υποκλάσεις
    protected void performSearch(String query) {
        System.out.println("Αναζητήθηκε: " + query);
    }
}
