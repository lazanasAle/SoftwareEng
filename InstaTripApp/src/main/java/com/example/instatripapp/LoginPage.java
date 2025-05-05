package com.example.instatripapp;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginPage extends FormScreen{
    public LoginPage() {
        super("Σύνδεση Χρήστη", 700, 600);
        renderGrid(400);
        renderLabel("INSTATRIP");
        renderLoginForm();
    }

    private void renderLoginForm() {
        // Create labels and text fields for the login form
        Label usernameLabel = new Label("Όνομα Χρήστη:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Κωδικός Πρόσβασης:");
        PasswordField passwordField = new PasswordField();

        // Render the form
        renderFormElements(
                new Label[]{usernameLabel, passwordLabel},
                new TextField[]{usernameField, passwordField});
    }
}
