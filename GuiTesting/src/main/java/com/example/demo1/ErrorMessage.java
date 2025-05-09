package com.example.demo1;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ErrorMessage extends ContainerScreen{
    public ErrorMessage(String message) {
        super("Error");
        renderContainerLabel("Σφάλμα");
        renderErrorMessage(message);
    }
    private void renderErrorMessage(String errorMessage) {
        Label label = new Label(errorMessage);
        label.setId("error-message");
        container.add(label, 0, 1, 2, 1);
        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
}
