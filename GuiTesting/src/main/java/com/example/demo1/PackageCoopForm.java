package com.example.demo1;

public class PackageCoopForm extends FormScreen{
    private final String[] labels = new String[]{"Διαθέσιμα Άτομα", "Ημερομηνία Συνεργασίας", "Ημερομηνία Λήξης Συνεργασίας", "Ώρα Έναρξης", "Τιμή:"};

    public PackageCoopForm() {
        super("Φόρμα Συνεργασίας Πακέτου");
        renderWelcomeLabel("Συμπληρώστε τη φόρμα συνεργασίας:");
        renderFormDetails(labels);
        renderFormSubmissionButton();
    }
}
