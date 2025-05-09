package com.example.instatripapp;

public class RemoveCooperationConfirmScreen extends ConfirmScreen {

    public RemoveCooperationConfirmScreen() {
        super("Οθόνη Επιβεβαίωσης Διαγραφής", "Είστε σίγουρος ότι θέλετε να διαγράψετε τη συνεργασία;");
        
        // Προσαρμογή της επιβεβαίωσης
        confirmButton.setOnAction(e -> {
            System.out.println("Η συνεργασία διαγράφηκε.");
            stage.close();
        });
    }
}
