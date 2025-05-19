package com.example.demo1;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class LayoutScreen extends Screen {
    protected GridPane mainGrid;
    protected GridPane container;
    protected VBox navigation;
    protected HBox hBox;
    public LayoutScreen (String title) {
        super(title);
        renderMainGrid();
        renderNavMenu();
        addBackButton();
//        renderContainer(700, 150);
    }
    private void addBackButton(){
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("back-button");
        navigation.getChildren().add(backButton);
    }
    private void renderMainGrid(){
        StackPane root = new StackPane();
        mainGrid = new GridPane();
        mainGrid.setHgap(100);
        mainGrid.setVgap(10);
        mainGrid.setPrefSize(widthOfScreen, heightOfScreen);
        StackPane.setAlignment(mainGrid, Pos.CENTER);
        // Add grid to root
        root.getChildren().addAll(mainGrid);

        Scene scene = new Scene(root, widthOfScreen, heightOfScreen);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/universal_styling.css")).toExternalForm());
        stage.setScene(scene);
    }
    private void renderNavMenu() {
        navigation = new VBox();
        navigation.setSpacing(10);
        navigation.setPrefHeight(heightOfScreen); // Optional for visual spacing
        navigation.setPrefWidth(334);
        navigation.setPadding(new Insets(20));
        navigation.setAlignment(Pos.TOP_LEFT);
        navigation.getStyleClass().add("sidebar");
        mainGrid.add(navigation, 0, 0);
    }
    protected void renderWelcomeLabel(String text) {
        Label welcomeLabel = new Label(text);
        welcomeLabel.setWrapText(true);// Set the maximum width for the text
        welcomeLabel.setId("welcome-label");
        navigation.getChildren().add(welcomeLabel);
    }
    protected void renderContainer(int width, int height){
        container = new GridPane();
        container.setId("container");
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(width, height);
        container.setMaxHeight(500);
        container.setVgap(10);
        container.setHgap(10);
        mainGrid.add(container, 1, 0);
    }
    protected void renderContainerLabel(String label) {
        Label label1 = new Label(label);
        label1.setId("container-label");
        container.add(label1, 0, 0, 2, 1); // Span 4 columns
        GridPane.setHalignment(label1, HPos.CENTER); // Center the label in the grid cell
    }
    protected void renderButtonsInNavigation(String[] texts){
        for (String text : texts) {
            Button button = new Button(text);
            button.getStyleClass().add("sidebarButton");
            navigation.getChildren().add(button);
        }
    }
    protected void renderHorizontalBar(){
        hBox = new HBox();
        hBox.setId("horizontal-bar");
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(20));
        navigation.getChildren().add(hBox);
        renderProfileIcon();
        renderProfileButton();
        renderLogOutButton();
    }
    private void renderLogOutButton() {
        Button logOutButton = new Button();
        logOutButton.getStyleClass().addAll("sidebarButton","exitButton");
        hBox.getChildren().add(logOutButton);
    }
    private void renderProfileIcon() {
        Button profileButton = new Button();
        profileButton.getStyleClass().add("profile-icon");
        hBox.getChildren().add(profileButton);
    }
    private void renderProfileButton() {
        Label profileLabel = new Label("Όνομα Χρήστη");
        profileLabel.getStyleClass().add("profile-label");
        hBox.getChildren().add(profileLabel);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.NEVER);
        spacer2.setMinWidth(50); // 50px spacing
        hBox.getChildren().add(spacer2);

    }
    protected void renderInstruction(String text) {
        Label instructionsLabel = new Label(text);
        instructionsLabel.setWrapText(true);// Set the maximum width for the text
        instructionsLabel.setId("instructions-label");
        container.add(instructionsLabel, 0, 1, 2, 1); // Span 4 columns
        GridPane.setHalignment(instructionsLabel, HPos.CENTER); // Center the label in the grid cell
    }


}
