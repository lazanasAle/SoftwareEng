package com.example;

public class PackageConfirmScreen extends ConfirmScreen {

    public PackageConfirmScreen() {
        super("Οθόνη Επιβεβαίωσης Πακέτου", "Είστε σίγουρος ότι θέλετε να δημιουργήσετε το πακέτο;");
        
        // Προσαρμογή της επιβεβαίωσης
        confirmButton.setOnAction(e -> {
            System.out.println("Πακέτο δημιουργήθηκε με επιτυχία.");
            stage.close();
        });
    }
}
