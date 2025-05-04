package com.example.instatravelgui;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class ErrorMessage extends Screen {
    public ErrorMessage(String message) {
        // screen methods
        super("Παράθυρο Σφάλματος", 400, 200);
        renderLabel("ΣΦΑΛΜΑ:");
        displayMessage(message);

    }
    public void displayMessage(String message) {
        Text errorMessage = new Text(message);
        Button okButton = new Button("OK");
        errorMessage.setStyle("-fx-font-size: 16px; -fx-fill: red; -fx-text-alignment: center; -fx-alignment: center;");
        okButton.setOnAction(e -> stage.close());
        // place the error message and button in the grid
        grid.add(errorMessage, 0, 1);
        grid.add(okButton, 0, 2);
        GridPane.setHalignment(errorMessage, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(okButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
}