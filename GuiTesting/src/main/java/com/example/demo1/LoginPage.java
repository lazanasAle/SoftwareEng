package com.example.demo1;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LoginPage extends ContainerScreen {
    public LoginPage() {
        super("Σύνδεση");
        renderContainerLabel("InstaTrip");
        renderContainerLabels(new String[]{"Όνομα Χρήστη:", "Κωδικός Πρόσβασης:"});
        renderSubmissionButtons();
    }

    private void renderContainerLabels(String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.getStyleClass().add("login-label");
            TextField textField = new TextField();
            textField.setId("loginField");
            container.add(label, 0, i + 1);
            container.add(textField, 1, i + 1);
        }
        // Add a checkbox for terms and conditions
        CheckBox checkBox = new CheckBox("Αποδοχή Όρων και Προϋποθέσεων");
        container.add(checkBox, 0, container.getChildren().size() - 1, 2, 1);
        GridPane.setHalignment(checkBox, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }

    private void renderSubmissionButtons() {
        // Create submit and clear buttons
        Button submitButton = new Button("Σύνδεση");
        submitButton.getStyleClass().add("loginButton");
        Button createAccountButton = new Button("Δημιουργία Λογαριασμού");
        createAccountButton.getStyleClass().add("loginButton");
        container.add(submitButton, 0, 10);
        container.add(createAccountButton, 1, 10);
        GridPane.setHalignment(submitButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
}