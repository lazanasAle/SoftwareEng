package com.example.instatripapp;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReservationFormScreen extends FormScreen {
    public ReservationFormScreen() {
        super("Κράτηση Διαμονής", 700, 600);
        renderGrid(400);
        renderLabel("Φόρμα Κράτησης Διαμονής");
        renderReservationForm();
    }

    private void renderReservationForm() {
        Label nameLabel = new Label("Όνομα:");
        TextField nameTextArea = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextArea = new TextField();
        Label phoneLabel = new Label("Τηλέφωνο:");
        TextField phoneTextArea = new TextField();
        Label dateLabel = new Label("Ημερομηνία:");
        TextField dateTextArea = new TextField();
        Label peopleLabel = new Label("Αριθμός Ατόμων:");
        TextField peopleTextArea = new TextField();
        // renderForm Details
        renderFormElements(
                new Label[]{nameLabel, emailLabel, phoneLabel, dateLabel,peopleLabel},
                new TextField[]{nameTextArea, emailTextArea, phoneTextArea, dateTextArea, peopleTextArea});
    }

}
