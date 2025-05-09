package com.example.demo1;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class LayoutScreen extends Screen {
    protected GridPane mainGrid;
    protected VBox navigation;
    public LayoutScreen (String title) {
        super(title);
        renderMainGrid();
        renderNavMenu();
        renderContainer(700, 150);
    }
    private void renderMainGrid(){
        StackPane root = new StackPane();
        mainGrid = new GridPane();
        mainGrid.setId("main-grid");
        mainGrid.setHgap(100);
        mainGrid.setVgap(10);
        mainGrid.setPrefSize(widthOfScreen, heightOfScreen);
        StackPane.setAlignment(mainGrid, Pos.CENTER);
        // Add grid to root
        root.getChildren().addAll(mainGrid);

        Scene scene = new Scene(root, widthOfScreen, heightOfScreen);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style_for_main_screen.css")).toExternalForm());
        stage.setScene(scene);
    }
    private void renderNavMenu() {
        navigation = new VBox();
        navigation.setSpacing(10);
        navigation.setPrefHeight(heightOfScreen); // Optional for visual spacing
        navigation.setPrefWidth(334);
        navigation.setPadding(new Insets(20));
        navigation.setAlignment(Pos.CENTER);
        navigation.getStyleClass().add("sidebar");
        mainGrid.add(navigation, 0, 0);
        renderWelcomeLabel("Welcome to InstaTrip");
    }
    protected void renderWelcomeLabel(String text) {
        Label welcomeLabel = new Label(text);
        welcomeLabel.getStyleClass().add("welcomeLabel");
        navigation.getChildren().add(welcomeLabel);
    }
    public void renderContainer(int width, int height){
        container = new GridPane();
        container.setId("container");
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(width, height);
        container.setMaxHeight(500);
        container.setVgap(10);
        container.setHgap(10);
        mainGrid.add(container, 1, 0);
    }
    protected void renderButtonsInNavigation(String[] texts){
        for (String text : texts) {
            Button button = new Button(text);
            button.getStyleClass().add("sidebarButton");
            navigation.getChildren().add(button);
        }
        renderLogOutButton();
    }
    private void renderLogOutButton() {
        Button logOutButton = new Button();
        logOutButton.getStyleClass().addAll("sidebarButton","exitButton");
        navigation.getChildren().add(logOutButton);
    }


}
