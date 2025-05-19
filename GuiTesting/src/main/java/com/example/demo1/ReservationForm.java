package com.example.demo1;

public class ReservationForm extends FormScreen {
    private final String[] labels = new String[]{"Όνομα:", "Επώνυμο:", "Email:", "Τηλέφωνο:", "Διαθέσιμα άτομα:"};

    public ReservationForm() {
        super("Φόρμα Κράτησης");
        renderWelcomeLabel("Συμπληρώστε τη φόρμα κράτησης:");
        renderFormDetails(labels);
    }

}
