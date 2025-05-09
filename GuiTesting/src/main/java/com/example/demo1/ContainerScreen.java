package com.example.demo1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class ContainerScreen extends Screen{
    protected GridPane container;
    public ContainerScreen(String title) {
        super(title);
        renderContainer();
    }
    private void renderContainer(){
        StackPane root = new StackPane();
        container = new GridPane();
        container.setId("container");
        container.setHgap(10);
        container.setVgap(10);
        container.setMaxWidth(500);
        container.setMaxHeight(500);
        container.setAlignment(Pos.CENTER);
        StackPane.setAlignment(container, Pos.CENTER);
        // Add grid to root
        root.getChildren().addAll(container);

        Scene scene = new Scene(root, widthOfScreen, heightOfScreen);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style_for_main_screen.css")).toExternalForm());
        stage.setScene(scene);
    }
    public void renderContainerLabels(String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.getStyleClass().add("login-label");
            TextField textField = new TextField();
            textField.getStyleClass().add("loginField");
            container.add(label, 0, i + 1);
            container.add(textField, 1, i + 1);
        }
    }
    protected void renderSubmissionButtons(){
        // Create submit and clear buttons
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");
//        clearButton.setOnAction(e-> clearFields(nameField));
        container.add(submitButton, 0, 9);
        container.add(clearButton, 1, 9);
        GridPane.setHalignment(submitButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(clearButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }

}
