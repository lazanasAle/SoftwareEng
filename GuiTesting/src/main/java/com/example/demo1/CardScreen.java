package com.example.demo1;

public class CardScreen extends FormScreen{
    private final String[] labels;
    public CardScreen() {
        super("Στοχεία Πληρωμής Κάρτας");
        labels = new String[]{"Όνομα Κατόχου", "Αριθμός Κάρτας", "Ημερομηνία Λήξης", "CVV"};
        renderWelcomeLabel("Εισάγετε τα στοιχεία της κάρτας σας:");
        renderFormDetails(labels);
        renderCheckbox("Αποθήκευση στοιχείων κάρτας");
        renderFormSubmissionButton();
    }
}
