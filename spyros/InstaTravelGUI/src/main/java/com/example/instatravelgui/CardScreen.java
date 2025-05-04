package com.example.instatravelgui;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CardScreen extends FormScreen {
    public CardScreen() {
        super("Πληρωμή κάρτας", 700, 300);
        renderCardForm();
    }

    private void renderCardForm() {
        // Create labels and text fields for the card form
        Label cardNumberLabel = new Label("Αριθμός Κάρτας:");
        TextField cardNumberField = new TextField();
        Label expiryDateLabel = new Label("Ημερομηνία Λήξης (MM/YY):");
        TextField expiryDateField = new TextField();
        Label cvvLabel = new Label("CVV:");
        TextField cvvField = new TextField();

        // Submit and Reset buttons are added through the FormScreen class

        // Render the form
        renderFormElements(new Label[]{cardNumberLabel, expiryDateLabel, cvvLabel}, new TextField[]{cardNumberField, expiryDateField, cvvField});
    }

}
