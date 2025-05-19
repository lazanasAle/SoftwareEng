package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.Arrays;

public class PaymentScreen extends FormScreen{
//    String[] labels = new String[]{"Όνομα Κατόχου Κάρτας:", "Αριθμός Κάρτας:", "Ημερομηνία Λήξης:", "CVV:"};
    private final String[] labels;
    public PaymentScreen() {
        super("Οθόνη Πληρωμής");
        labels = new String[]{"Πληρωμή με κάρτα","Πληρωμή με μετρητά"};
        renderWelcomeLabel("Εισάγετε τα στοιχεία πληρωμής:");
        renderDropdown(labels);
        renderFormSubmissionButton();
    }

}
