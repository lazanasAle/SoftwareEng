package com.example.instatripapp;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class SearchHistoryScreen extends SearchScreen {


    public SearchHistoryScreen(SearchContent content) {
        super("Ιστορικό Αναζητήσεων", 1000, 750, content);
        renderLabel("Αναζήτηση Ιστορικού");
        renderGrid(500);
    }


    @Override
    protected void performSearch(String query, SearchContent content) {
        resultsList.getItems().clear();
        resultsList.setVisible(true); // Show the field when a search is performed

        // dummy αποτελεσματα 
        if (!query.isBlank()) {
            resultsList.getItems().addAll(
                "Αναζήτηση: Πακέτο Σαντορίνης - 10/04/2025",
                "Κράτηση ξενοδοχείου στην Κέρκυρα - 15/03/2025",
                "Εκδρομή στο Πήλιο - 01/01/2025"
            );
        } else {
            resultsList.getItems().add("Εισάγετε όρο για να δείτε σχετικό ιστορικό.");
        }
    }
}
