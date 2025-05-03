package com.example.instatravelgui;

import javafx.scene.control.*;

public class ReservationFormScreen extends FormScreen {
    public ReservationFormScreen() {
        super("Δημιουργία Κράτησης", 1000, 400);
        renderLabel("Παρακαλούμε συμπληρώστε τα στοιχεία σας για να κάνετε κράτηση:");
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
