package com.example;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SearchHistoryScreen extends SearchScreen {

    private ListView<String> resultsList;

    public SearchHistoryScreen() {
        renderStage("Οθόνη Αναζήτησης Ιστορικού", 500, 350);
        addInstructions();
        addResultsList();
    }

    private void addInstructions() {
        Label instructions = new Label("Αναζητήστε στο ιστορικό κρατήσεων ή προηγούμενων ενεργειών:");
        grid.add(instructions, 0, 3);
    }

    private void addResultsList() {
        resultsList = new ListView<>();
        grid.add(resultsList, 0, 4);
    }

    @Override
    protected void performSearch(String query) {
        System.out.println("Αναζήτηση στο ιστορικό για: " + query);
        resultsList.getItems().clear();

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
