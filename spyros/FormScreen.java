package com.example.instatravelgui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class FormScreen extends Screen{
    private final TextField []nameField;
    private int gridPosition = 1; // because 0 is the label

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
    protected void renderFormButtons(Label[] labels, Button[] buttons) {
        for(int i=0; i<labels.length; i++) {
            grid.add(labels[i], 0, gridPosition+i+1);
            grid.add(buttons[i], 1, gridPosition+i+1);

        }
        gridPosition += labels.length + 1;
    }
    private void renderFormSubmissionButtons(){
        // Create submit and clear buttons
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(_ -> clearFields(nameField));
        grid.add(submitButton, 0, 9);
        grid.add(clearButton, 1, 9);
    }
    private void clearFields(TextField[] textFields) {
        for (TextField textField : textFields) {
            if(textField!=null) {
                textField.clear();
            }
        }

    }

}
