package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.scene.control.*;

public class FormScreen extends LayoutScreen{
    public FormScreen(String windowTitle){
        super(windowTitle);
    }
    protected void renderFormDetails(String[] labels){
        for (String label : labels) {
            Label formLabel = new Label(label);
            TextField formField = new TextField();
            formField.getStyleClass().add("text-input");
            formLabel.getStyleClass().add("labels");
            navigation.getChildren().add(formLabel);
            navigation.getChildren().add(formField);
        }
    }
    protected void renderCheckbox(String checkboxTitle){
        CheckBox saveCardDetails = new CheckBox(checkboxTitle);
        navigation.getChildren().add(saveCardDetails);
        saveCardDetails.getStyleClass().add("checkbox");
    }
    protected void renderDropdown(String[] choices){
        ComboBox<String> dropdown = new ComboBox<>(FXCollections.observableArrayList(choices));
        dropdown.getStyleClass().add("dropdown");
        dropdown.setValue(choices[0]);
        navigation.getChildren().add(dropdown);
    }
    protected void renderFormSubmissionButton(){
        Button submitButton = new Button("Υποβολή");
        submitButton.getStyleClass().add("submit-button");
        navigation.getChildren().add(submitButton);
    }
}
