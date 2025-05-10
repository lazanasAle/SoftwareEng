package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class FormScreen extends Screen{
    private final TextField []nameField;
    private int gridPosition = 1; // because 0 is the label
    protected Button submitButton;
    protected Button clearButton;

    public FormScreen(String title, int width, int height) {
        super(title, width, height);
        nameField = new TextField[10];// Compulsory for all forms
        renderFormSubmissionButtons();
    }

    protected void renderFormElements(Label[] labels, TextField[] textFields) {
        for (int i = 0; i < labels.length; i++) {
            grid.add(labels[i], 0, gridPosition + i + 1);
            grid.add(textFields[i], 1, gridPosition + i + 1);
            nameField[i] = textFields[i];
        }
        gridPosition += labels.length + 1;
    }

    protected void renderFormElements(Label[] labels, TextField[] textFields, DatePicker[] datePickers, Label[] dateLabels) {

        renderFormElements(labels, textFields);
        for(int j=0; j<datePickers.length; ++j){
            grid.add(dateLabels[j], 0, gridPosition+j+1);
            grid.add(datePickers[j], 1, gridPosition+j+1);
        }
        gridPosition+=datePickers.length;
    }

    protected void renderFormButtons(Label[] labels, Button[] buttons) {
        for(int i=0; i<labels.length; i++) {
            grid.add(labels[i], 0, gridPosition+i+1);
            grid.add(buttons[i], 1, gridPosition+i+1);

        }
        gridPosition += labels.length + 1;
    }
    private void renderFormSubmissionButtons(){
        // Create submit and clear buttons
        submitButton = new Button("Submit");
        clearButton = new Button("Clear");
        clearButton.setOnAction(e-> clearFields(nameField));
        grid.add(submitButton, 0,9);
        grid.add(clearButton, 1, 9);
        GridPane.setHalignment(submitButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(clearButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
    private void clearFields(TextField[] textFields) {
        for (TextField textField : textFields) {
            if(textField!=null) {
                textField.clear();
            }
        }

    }

}
