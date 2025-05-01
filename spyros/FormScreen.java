package com.example.instatravelgui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class FormScreen extends Screen{
    public FormScreen(String title, int width, int height) {
        // screen methods
        renderGrid();
        renderStage(title, width, height);
    }
    protected void renderForm(String instructMessage, Label[] labels, TextField[] textFields) {
        // place the labels and text fields in the grid
        instructUser(instructMessage);
        for (int i = 0; i < labels.length; i++) {
            grid.add(labels[i], 0, i+1);
            grid.add(textFields[i], 1, i+1);
        }
        int buttonRow = Math.max(labels.length, textFields.length)+1;
        // Create submit and clear buttons
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");
        // Set button actions
        clearButton.setOnAction(e -> clearFields(textFields));
        // Add submit and clear button on the grid
        grid.add(submitButton, 0, buttonRow);
        grid.add(clearButton, 1, buttonRow);

    }
    private void clearFields(TextField[] textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }
    // ignore the warning
    private void instructUser(String message){
        Label instructionLabel = new Label(message);
        instructionLabel.setStyle("-fx-font-size: 16px; -fx-fill: black;");
        grid.add(instructionLabel, 0, 0);
    }
}
