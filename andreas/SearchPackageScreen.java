package com.example;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SearchPackageScreen extends SearchScreen {

    private ListView<String> resultsList;

    public SearchPackageScreen() {
        renderStage("Οθόνη Αναζήτησης Πακέτων", 500, 350);
        addInstructions();
        addResultsList();
    }

    private void addInstructions() {
        Label instructions = new Label("Αναζητήστε τουριστικά πακέτα με βάση προορισμό ή λέξη-κλειδί:");
        grid.add(instructions, 0, 3);
    }

    private void addResultsList() {
        resultsList = new ListView<>();
        grid.add(resultsList, 0, 4);
    }

    @Override
    protected void performSearch(String query) {
        System.out.println("Αναζήτηση πακέτων για: " + query);
        resultsList.getItems().clear();

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

