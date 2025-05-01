package com.example;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class PaymentScreen extends Screen {

    public PaymentScreen() {
        renderGrid();
        renderStage("Οθόνη Τρόπων Πληρωμής", 400, 250);

        Text label = new Text("Επιλέξτε τρόπο πληρωμής:");
        ComboBox<String> paymentMethods = new ComboBox<>();
        paymentMethods.getItems().addAll(
            "Πιστωτική Κάρτα",
            "Χρεωστική Κάρτα",
            "Μετρητά",
            "PayPal"
        );
        paymentMethods.setValue("Πιστωτική Κάρτα"); // default

        Button confirmButton = new Button("Επιβεβαίωση");
        confirmButton.setOnAction(e -> handlePaymentSelection(paymentMethods.getValue()));

        grid.add(label, 0, 0);
        grid.add(paymentMethods, 0, 1);
        grid.add(confirmButton, 0, 2);
    }

    private void handlePaymentSelection(String method) {
        System.out.println("Επιλεγμένος τρόπος πληρωμής: " + method);
        // ανοιγεις και νεα οθονη ή να κλεινεις αυτη
        stage.close();
    }
}
