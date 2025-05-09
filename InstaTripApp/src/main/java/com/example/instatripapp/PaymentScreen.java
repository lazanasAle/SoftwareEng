package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PaymentScreen extends Screen {

    public PaymentScreen() {
        super("Μενού Πληρωμής", 1000, 750);
        renderGrid(300);
        renderLabel("Εδώ μπορείτε να επιλέξετε τον τρόπο πληρωμής που επιθυμείτε!");
        renderMenu();
    }

    private void renderMenu() {
        ComboBox<String> paymentMethods = new ComboBox<>();
        paymentMethods.getItems().addAll("Μετρητά", "Κάρτα");

        paymentMethods.setValue("Πιστωτική Κάρτα"); // default
        Button confirmButton = new Button("Επιβεβαίωση");
        confirmButton.setOnAction(e -> handlePaymentSelection(paymentMethods.getValue()));
        grid.add(paymentMethods, 0, 3, 2, 1); // Span 2 columns
        grid.add(confirmButton, 0, 5, 2, 1); // Span 2 columns
        GridPane.setHalignment(paymentMethods, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(confirmButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }

    private void handlePaymentSelection(String method) {
        System.out.println("Επιλεγμένος τρόπος πληρωμής: " + method);
        // ανοιγεις και νεα οθονη ή να κλεινεις αυτη
        stage.close();
    }
}

