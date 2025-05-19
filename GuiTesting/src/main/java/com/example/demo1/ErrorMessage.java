package com.example.demo1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ErrorMessage extends ContainerScreen{
    public ErrorMessage(String message) {
        super("Error");
        renderContainerLabel("Σφάλμα");
        renderErrorMessage(message);
        renderCloseButton();
    }
    private void renderErrorMessage(String errorMessage) {
        Label label = new Label(errorMessage);
        label.setId("error-message");
        container.add(label, 0, 1, 2, 1);
        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
    private void renderCloseButton() {
        Button closeButton = new Button("Κλείσιμο");
        closeButton.setId("close-button");
        container.add(closeButton, 0, 2, 2, 1);
        GridPane.setHalignment(closeButton, javafx.geometry.HPos.CENTER); // Center the button in the grid cell
    }
}
