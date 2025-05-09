package com.example.demo1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginPage extends ContainerScreen{
    public LoginPage() {
        super("Σύνδεση");
        renderContainerLabel("InstaTrip");
        renderContainerLabels(new String[]{"Όνομα Χρήστη:", "Κωδικός Πρόσβασης:"});
        renderSubmissionButtons();
    }
}
