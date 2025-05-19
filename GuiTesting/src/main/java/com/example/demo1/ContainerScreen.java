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
        container.setMaxWidth(700);
        container.setMaxHeight(600);
        container.setAlignment(Pos.CENTER);
        StackPane.setAlignment(container, Pos.CENTER);
        // Add grid to root
        root.getChildren().addAll(container);

        Scene scene = new Scene(root, widthOfScreen, heightOfScreen);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/universal_styling.css")).toExternalForm());
        stage.setScene(scene);
    }

    public void renderContainerLabel(String label) {
        Label label1 = new Label(label);
        container.add(label1, 0, 0, 2, 1);
        label1.setId("container-label");
        GridPane.setHalignment(label1, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }


}
