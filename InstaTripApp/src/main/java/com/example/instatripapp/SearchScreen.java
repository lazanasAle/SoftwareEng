package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class SearchScreen extends Screen {
    protected TextField searchField;
    protected Button searchButton;
    protected ListView<String> resultsList;

    public SearchScreen(String title, int widthOfScreen, int heightOfScreen, SearchContent content) {
        super(title, widthOfScreen, heightOfScreen);
        renderGrid(500);
        setupSearchUI(content);
        addResultsList();
    }

     private void addResultsList() {
        resultsList = new ListView<>();
        resultsList.setPrefWidth(500); // Set a preferred height for the ListView
        grid.add(resultsList, 0, 4);
        resultsList.setVisible(false); // Hide the field initially
    }

    protected void setupSearchUI(SearchContent content) {
        searchField = new TextField();
        searchButton = new Button("Αναζήτηση");

        searchButton.setOnAction(e -> {
            try {
                String txt = searchField.getText();
                content.content=txt;
                performSearch(txt, content);
            } catch (IOException ex) {
                ScreenRedirect.launchErrorMsg("Σφάλμα Ι/Ο");
            }

        });

        grid.add(searchField, 0, 1);
        grid.add(searchButton, 0, 2, 2, 1); // Span 2 columns
        GridPane.setHalignment(searchButton, javafx.geometry.HPos.CENTER); // Center the button in the grid cell
    }

    // Μεθοδος για override από τις υποκλάσεις
    protected void performSearch(String query, SearchContent content) throws IOException {
        resultsList.setVisible(true);
    }
}
