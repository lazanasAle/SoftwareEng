package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreatePackageForm extends FormScreen{

    public CreatePackageForm() {
        super("Δημιουργία Πακέτου", 700, 500);
        renderGrid(200);
        renderLabel("Συμπληρώστε τα στοιχεία του πακέτου σας");
        renderPackageForm();
    }

    private void renderPackageForm() {
        Label regionLabel = new Label("Γεωγραφική Περιοχή:");
        TextField regionTextArea = new TextField();
        Label availablePeopleLabel = new Label("Μέγιστο Ποσό Εκδρομέων:");
        TextField availablePeopleTextArea = new TextField();
        Label priceLabel = new Label("Εκτιμώμενη Τιμή:");
        TextField priceTextArea = new TextField();
        Label imageLabel = new Label("Εικόνες για ταξίδια:");
        Button uploadButton = new Button("Upload");

        // renderForm Details
        renderFormElements(
            new Label[]{regionLabel, availablePeopleLabel, priceLabel},
            new TextField[]{regionTextArea, availablePeopleTextArea, priceTextArea});
        renderFormButtons(new Label[]{imageLabel}, new Button[]{uploadButton});

    }
}
