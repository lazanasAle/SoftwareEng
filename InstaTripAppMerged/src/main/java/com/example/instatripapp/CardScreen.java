package com.example.instatripapp;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CardScreen extends FormScreen {
    Package pkg;
    public CardScreen(Package pkg) {
        super("Πληρωμή κάρτας", 700, 700);
        renderGrid(500);
        this.pkg=pkg;
        renderLabel("Τα στοιχεία κάρτας:");
        renderCardForm();
    }

    private void renderCardForm() {
        // Create labels and text fields for the card form
        Label Ownername= new Label("Ονομα κατοχου:");
        TextField OwnerNameField = new TextField();
        Label cardNumberLabel = new Label("Αριθμός Κάρτας:");
        TextField cardNumberField = new TextField();
        Label expiryDateLabel = new Label("Ημερομηνία Λήξης (MM/YY):");
        TextField expiryDateField = new TextField();
        Label cvvLabel = new Label("CVV:");
        TextField cvvField = new TextField();

        // Submit and Reset buttons are added through the FormScreen class

        submitButton.setOnAction(e->{
            CardDescription cardDescription=new CardDescription(Long.parseLong(cardNumberField.getText()),OwnerNameField.getText(),Short.parseShort(cvvField.getText()),pkg);
        });

        // Render the form
        renderFormElements(new Label[]{Ownername,cardNumberLabel, expiryDateLabel, cvvLabel}, new TextField[]{OwnerNameField,cardNumberField, expiryDateField, cvvField});
    }

}
