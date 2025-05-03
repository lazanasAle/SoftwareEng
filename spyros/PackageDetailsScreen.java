package com.example.instatravelgui;

import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;
public class PackageDetailsScreen extends Screen {;
    public PackageDetailsScreen() {
        // screen methods
        super("Package Details", 500, 300);
        renderLabel("Λεπτομέρειες Πακέτου");
        renderPackageDetails();
    }

    private void renderPackageDetails() {
        PackageGUI packageGUI = new PackageGUI("1", "Sample Package", "This is a sample package description.", "100€");


        Text idText = new Text("ID: " + packageGUI.getId());
        idText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #333;");

        Text nameText = new Text("Name: " + packageGUI.getName());
        nameText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #333;");

        Text descriptionText = new Text("Description: " + packageGUI.getDescription());
        descriptionText.setStyle("-fx-font-size: 14px; -fx-fill: #555;");

        Text priceText = new Text("Price: " + packageGUI.getPrice());
        priceText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #228B22;");

        // Add the package details to the grid
        Text[] labels = {idText, nameText, descriptionText, priceText};

        Button backButton = new Button("Πληρωμή");
        backButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 10;");
        Button cooperationButton = new Button("Συνεργασία");
        cooperationButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 10;");
        Button[] buttons = {backButton, cooperationButton};
        addElementsToGrid(labels, buttons);
    }
    private void addElementsToGrid(Text[] label,Button[] button) {
        for(int i = 0; i < label.length; i++) {
            grid.add(label[i], 0, gridPosition + i + 1);
            GridPane.setHalignment(label[i], javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        }
        gridPosition += label.length + 1; // Update the grid position for buttons
        for(int i = 0; i < button.length; i++) {
            grid.add(button[i], i, gridPosition);

        }
    }
}
