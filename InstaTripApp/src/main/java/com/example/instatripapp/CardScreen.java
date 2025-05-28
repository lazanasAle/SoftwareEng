package com.example.instatripapp;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CardScreen extends FormScreen {
    public CardScreen(Package pkg) {
        super("Πληρωμή κάρτας", 700, 700);
        renderGrid(500);
        renderLabel("Τα στοιχεία κάρτας:");
        renderCardForm(pkg);
    }

    private void renderCardForm(Package pkg) {
        // Create labels and text fields for the card form
        Label cardNumberLabel = new Label("Αριθμός Κάρτας:");
        TextField cardNumberField = new TextField();
        Label expiryDateLabel = new Label("Ημερομηνία Λήξης (MM/YY):");
        Label ownerName = new Label("Όνομα Κατόχου");
        TextField ownerField = new TextField();
        TextField expiryDateField = new TextField();
        Label cvvLabel = new Label("CVV:");
        TextField cvvField = new TextField();

        // Submit and Reset buttons are added through the FormScreen class
        submitButton.setOnAction(event -> {
                boolean condition = cardNumberField.getText().isEmpty() || expiryDateField.getText().isEmpty() || cvvField.getText().isEmpty() || ownerField.getText().isEmpty();
                if(condition){
                    ScreenRedirect.launchErrorMsg("Συμπληρώστε όλα τα στοιχεία της φόρμας");
                    return;
                }
                try {
                    long cardNumber = Long.parseLong(cardNumberField.getText());
                    if((cardNumber<Long.parseLong("1000000000000000") || cardNumber>Long.parseLong("9999999999999999"))){
                        ScreenRedirect.launchErrorMsg("Συμπληρώστε σωστά τον αριθμό στα στοιχεία της φόρμας");
                        return;
                    }
                    String ownersName = ownerField.getText();
                    if(ownersName.matches(".*\\d.*")){
                        ScreenRedirect.launchErrorMsg("Συμπληρώστε σωστά το όνομα κατόχου από τα στοιχεία της φόρμας");
                        return;
                    }
                    String expDate = expiryDateField.getText();
                    if (!expDate.matches("(0[1-9]|1[0-2])/\\d{2}")){
                        ScreenRedirect.launchErrorMsg("Συμπληρώστε σωστά την ημερομηνία λήξης από τα στοιχεία της φόρμας");
                        return;
                    }
                    short cvv = Short.parseShort(cvvField.getText());
                    if(cvv<100 || cvv>999){
                        ScreenRedirect.launchErrorMsg("Συμπληρώστε σωστά τα στοιχεία της φόρμας");
                    }
                    CardDescription cd = new CardDescription(cardNumber, ownersName, cvv);
                    BankCommunicationAPI api = new BankCommunicationAPI();
                    if(api.checkCard(cd)){
                        if(api.tryCharging(cd)){
                            FinalReservation temporaryReservation = new FinalReservation(pkg);
                        }
                        else {
                            ScreenRedirect.launchErrorMsg("Αδυναμία Χρέωσης κάρτας");
                        }
                    }
                    else {
                        ScreenRedirect.launchErrorMsg("Λανθασμένα στοιχεία Κάρτας");
                    }
                }
                catch (NumberFormatException exep){
                    ScreenRedirect.launchErrorMsg("Συμπληρώστε σωστά τα στοιχεία της φόρμας");
                }
                this.stage.close();

        });
        // Render the form
        renderFormElements(new Label[]{cardNumberLabel, expiryDateLabel, ownerName, cvvLabel}, new TextField[]{cardNumberField, expiryDateField, ownerField, cvvField});
    }


}
