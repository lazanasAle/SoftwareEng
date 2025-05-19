package com.example.demo1;

import javafx.scene.control.Button;

public class CreatePackageForm extends FormScreen{
    public CreatePackageForm() {
        super("Δημιουργία Πακέτου");
        renderWelcomeLabel("Εισάγετε τα στοιχεία του πακέτου σας");
        renderFormDetails(new String[]{"Όνομα Πακέτου:", "Προορισμός:", "Ημερομηνία Αναχώρησης:", "Ημερομηνία Επιστροφής:", "Τιμή:"});
    }
}
