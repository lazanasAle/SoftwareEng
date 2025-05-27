package com.example.instatripapp;

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
                new TextField[]{nameTextArea, emailTextArea, phoneTextArea, peopleTextArea}
        );

        submitButton.setOnAction(e-> {
            boolean condition = nameTextArea.getText().isEmpty() || emailTextArea.getText().isEmpty() || phoneTextArea.getText().isEmpty() || peopleTextArea.getText().isEmpty();
            String name = nameTextArea.getText();
            String phone = phoneTextArea.getText();
            Integer people;
            try{
                people=Integer.parseInt(peopleTextArea.getText());
            }catch (NumberFormatException nme){
                ScreenRedirect.launchErrorMsg("Εισάγετε σωστά τα στοιχεία της φόρμας");
                return;
            }
            if (condition) {
                ScreenRedirect.launchErrorMsg("Δεν εισαγεται στοιχεια σε ολα τα πεδία");
            }
            else if (name.matches(".*\\d.*") || !phone.matches("\\d+")){
                ScreenRedirect.launchErrorMsg("Εισάγετε σωστά τα στοιχεία της φόρμας");
            }
            else {
                pkg.setPeople(people);
                PaymentScreen paymentScreen=new PaymentScreen(pkg);
            }
        });
    }

}
