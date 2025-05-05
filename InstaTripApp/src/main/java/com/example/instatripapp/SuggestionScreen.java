package com.example.instatripapp;

import java.util.Arrays;

public class SuggestionScreen extends ListScreen {
    public SuggestionScreen() {
        super("Προτεινόμενα αποτελέσματα", 600, 600);
        renderGrid(500);
        renderSuggestions();
    }

    private void renderSuggestions() {
        renderLabel("Αποτελέσματα αναζήτησης για εσάς");

        // Dummy data for demonstration
        PackageGUI [] suggestions = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Επιλογή";

        renderArray(columnNames, Arrays.asList(suggestions), propertyNames, buttonName);
    }
}
