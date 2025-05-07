package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SearchScreen extends Screen {
    protected TextField searchField;
    protected Button searchButton;
    protected ListView<String> resultsList;

    public SearchScreen(String title, int widthOfScreen, int heightOfScreen) {
        super(title, widthOfScreen, heightOfScreen);
        renderGrid(500);
        setupSearchUI();
        addResultsList();
    }

     private void addResultsList() {
        resultsList = new ListView<>();
        resultsList.setPrefWidth(500); // Set a preferred height for the ListView
        grid.add(resultsList, 0, 4);
        resultsList.setVisible(false); // Hide the field initially
    }

    protected void setupSearchUI() {
        searchField = new TextField();
        searchButton = new Button("Αναζήτηση");

        searchButton.setOnAction(e -> performSearch(searchField.getText()));

        grid.add(searchField, 0, 1);
        grid.add(searchButton, 0, 2, 2, 1); // Span 2 columns
        GridPane.setHalignment(searchButton, javafx.geometry.HPos.CENTER); // Center the button in the grid cell
    }

    // Μεθοδος για override από τις υποκλάσεις
    protected void performSearch(String query) {
        resultsList.setVisible(true);
    }
}
