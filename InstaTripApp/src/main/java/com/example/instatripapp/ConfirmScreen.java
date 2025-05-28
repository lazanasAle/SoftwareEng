package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import java.util.function.Consumer;

public class ConfirmScreen extends Screen {

    protected Text messageText;
    protected Button confirmButton;
    protected Button cancelButton;
    private Consumer<Integer> onSubmit;

    public ConfirmScreen(String title, String message, Consumer<Integer> onSubmit) {
        super(title, 1000, 750);
        this.onSubmit=onSubmit;
        renderGrid(300);
        renderLabel("Επιβεβαίωση");

        renderConfirmationMessage(message);
        renderButtons();
    }

    private void renderConfirmationMessage(String message) {
        messageText = new Text(message);
        grid.add(messageText, 0, 1, 2, 1);
        GridPane.setHalignment(messageText, HPos.CENTER);
    }

    private void renderButtons() {
        confirmButton = new Button("Επιβεβαίωση");
        cancelButton = new Button("Άκυρο");
        cancelButton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white;");

        // Ευθυγράμμιση οριζόντια σε κοινό HBox
        HBox buttonBox = new HBox(30); // 30 px κενό μεταξύ κουμπιών
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(confirmButton, cancelButton);

        // Προσθήκη του HBox στο grid
        grid.add(buttonBox, 0, 2, 2, 1); // Καταλαμβάνει 2 στήλες

        confirmButton.setOnAction(e -> {
            onSubmit.accept(1);
            stage.close();
        });

        cancelButton.setOnAction(e -> {onSubmit.accept(0); stage.close();});

    }
}

