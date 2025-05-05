package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
public class PackageDetailsScreen extends Screen {;
    private int gridPosition = 1;
    public PackageDetailsScreen() {
        // screen methods
        super("Package Details", 800, 700);
        renderGrid(600);
        renderLabel("Λεπτομέρειες Πακέτου");
        renderPackageDetails();
    }

    private void renderPackageDetails() {
        PackageGUI packageGUI = new PackageGUI("1", "Sample Package", "This is a sample package description.", "100€");


        Text idText = new Text("ID: " + packageGUI.getId());

        Text nameText = new Text("Name: " + packageGUI.getName());

        Text descriptionText = new Text("Description: " + packageGUI.getDescription());

        Text priceText = new Text("Price: " + packageGUI.getPrice());

        // Add the package details to the grid
        Text[] labels = {idText, nameText, descriptionText, priceText};

        Button backButton = new Button("Πληρωμή");
        Button cooperationButton = new Button("Συνεργασία");
        Button[] buttons = {backButton, cooperationButton};
        addElementsToGrid(labels, buttons);
    }
    private void addElementsToGrid(Text[] label,Button[] button) {
        for(int i = 0; i < label.length; i++) {
            grid.add(label[i], 0, gridPosition + i + 1, 2, 1); // Add label to the grid
            GridPane.setHalignment(label[i], javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        }
        gridPosition += label.length + 1; // Update the grid position for buttons
        for(int i = 0; i < button.length; i++) {
            grid.add(button[i], 0, gridPosition + i + 1, 2, 1); // Add button to the grid
            GridPane.setHalignment(button[i], javafx.geometry.HPos.CENTER); // Center the button in the grid cell

        }
    }
}
