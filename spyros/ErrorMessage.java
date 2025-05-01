package com.example.instatravelgui;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class ErrorMessage extends Screen {
    public ErrorMessage(String message) {
        // screen methods
        renderGrid();
        renderStage("Παράθυρο Σφάλματος", 300, 200);
        displayMessage(message);

    }
    public void displayMessage(String message) {
        Text errorMessage = new Text(message);
        Button okButton = new Button("OK");
        errorMessage.setStyle("-fx-font-size: 16px; -fx-fill: red;");
        okButton.setOnAction(e -> stage.close());
        // place the error message and button in the grid
        grid.add(errorMessage, 0, 0);
        grid.add(okButton, 0, 1);
    }
}