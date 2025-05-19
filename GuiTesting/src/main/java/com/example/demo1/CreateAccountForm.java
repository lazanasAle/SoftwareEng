package com.example.demo1;

public class CreateAccountForm extends FormScreen {
    String[] labels = new String[]{"Όνομα:", "Επώνυμο:", "Email:", "Τηλέφωνο:", "Κωδικός:"};

    public CreateAccountForm() {
        super("Δημιουργία Λογαριασμού");
        renderWelcomeLabel("Συμπληρώστε τη φόρμα δημιουργίας λογαριασμού:");
        renderFormDetails(labels);
        renderDropdown(new String[]{"Ξενοδοχείο","Πελάτης","Τουριστικό Γραφείο"});

    }
}
