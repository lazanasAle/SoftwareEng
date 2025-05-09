package com.example.instatripapp;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SearchPackageScreen extends SearchScreen {


    public SearchPackageScreen() {
        super("Αναζήτηση Πακέτων", 1000, 750);
        renderLabel("Αναζήτηση Πακέτων");
        renderGrid(500);
    }


    @Override
    protected void performSearch(String query) {
        resultsList.getItems().clear();
        resultsList.setVisible(true); // Show the field when a search is performed

        // dummy αποτελεσματα 
        if (!query.isBlank()) {
            resultsList.getItems().addAll(
                "Πακέτο για Σαντορίνη - 5 μέρες",
                "Εκδρομή στο Πήλιο με ξεναγό",
                "Ορεινή Αρκαδία - Περιήγηση"
            );
        } else {
            resultsList.getItems().add("Παρακαλώ εισάγετε κάποιο όρο αναζήτησης.");
        }
    }
}

