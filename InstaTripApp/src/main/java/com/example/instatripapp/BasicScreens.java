package com.example.instatripapp;
import java.util.Arrays;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.util.Arrays;
import java.util.Objects;

// super class for all screens
class Screen{
    protected GridPane grid;
    protected Stage stage;
    protected final int widthOfScreen;
    protected final int heightOfScreen;
    protected final String screenTitle;

    public Screen(String title,int widthOfScreen, int heightOfScreen) {
        this.widthOfScreen = widthOfScreen;
        this.heightOfScreen = heightOfScreen;
        this.screenTitle = title;
        this.grid = new GridPane();
        this.stage = new Stage();

        renderStage();
    }

    private void renderStage(){
        stage.setTitle(screenTitle);
        stage.setResizable(false);
        stage.setWidth(widthOfScreen);
        stage.setHeight(heightOfScreen);
        stage.show();
    }

    protected void renderGrid(int sizeOfContainer) {
        StackPane root = new StackPane();

        grid.setAlignment(Pos.CENTER); // center contents inside grid
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setId("grid-box");
        grid.setMaxWidth(sizeOfContainer);
        grid.setMaxHeight(sizeOfContainer);


        // Add grid to root
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root, widthOfScreen, heightOfScreen);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        stage.setScene(scene);
    }

    protected void renderLabel(String label) {
        Text l = new Text(label);
        l.setId("welcome_label");
        grid.add(l, 0, 0 ,2,1); // Span 4 columns
        GridPane.setHalignment(l, HPos.CENTER); // Center horizontally
    }

}



// see comments in ListScreen.java
class MainScreen extends ListScreen {
    public MainScreen() {
        // screen methods
        super("Κύριο Μενού", 650, 500);
        renderGrid(300);
        renderLabel("Καλώς ήρθατε στο InstaTrip κύριε Μανωλάτο!");
        renderMenu();
    }

    private void renderMenu() {
        String[] menuOptions = {
                "Επιλογή Πακέτου",
                "Ιστορικό Πακέτων",
                "Φίλτρα",
                "Συνεργάτες",
                "Αξιολόγηση Πακέτου",
                "Πληρωμή Πακέτου"
        };
        renderList(Arrays.asList(menuOptions));
    }

}



class ErrorMessage extends Screen {
    public ErrorMessage(String message) {
        // screen methods
        super("Παράθυρο Σφάλματος", 400, 300);
        renderGrid(50);
        renderLabel("ΣΦΑΛΜΑ:");
        displayMessage(message);

    }
    public void displayMessage(String message) {
        Text errorMessage = new Text(message);
        Button okButton = new Button("OK");
        errorMessage.setId("error_message");
//        errorMessage.setStyle("-fx-background-color: red;");

        okButton.setOnAction(e -> stage.close());
        // place the error message and button in the grid
        grid.add(errorMessage, 0, 1,2,1);
        grid.add(okButton, 0, 2,2,1);
        GridPane.setHalignment(errorMessage, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(okButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
}

class LoginPage extends FormScreen{
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

