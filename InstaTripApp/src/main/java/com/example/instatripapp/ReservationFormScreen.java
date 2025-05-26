package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReservationFormScreen extends FormScreen {
    private Package pkg;
    private DataSourceManager manager;

    public ReservationFormScreen(Package pkg, DataSourceManager manager) {
        super("Κράτηση Διαμονής", 700, 600);
        renderGrid(400);
        renderLabel("Φόρμα Κράτησης Διαμονής");
        this.manager=manager;
        this.pkg=pkg;
        renderReservationForm();
    }

    private void renderReservationForm() {
        Label nameLabel = new Label("Όνομα:");
        TextField nameTextArea = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextArea = new TextField();
        Label phoneLabel = new Label("Τηλέφωνο:");
        TextField phoneTextArea = new TextField();
        Label peopleLabel = new Label("Αριθμός Ατόμων:");
        TextField peopleTextArea = new TextField(String.valueOf(pkg.getPeople()));




        renderFormElements(
                new Label[]{nameLabel, emailLabel, phoneLabel, peopleLabel},
                new TextField[]{nameTextArea, emailTextArea, phoneTextArea, peopleTextArea});


        submitButton.setOnAction(e-> {
            if (nameTextArea.getText().isEmpty() || emailTextArea.getText().isEmpty() || phoneTextArea.getText().isEmpty() || peopleTextArea.getText().isEmpty()) {
                ScreenRedirect.launchErrorMsg("Δεν εισαγεται στοιχεια σε ολα τα πεδία");
            }
            else {PaymentScreen paymentScreen=new PaymentScreen(pkg);}
        });
    }

}
