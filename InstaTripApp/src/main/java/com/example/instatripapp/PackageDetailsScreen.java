package com.example.instatripapp;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PackageDetailsScreen extends Screen {;
    private int gridPosition = 1;

    public PackageDetailsScreen(Package pkg, Button optionBtn) {
        // screen methods
        super("Package Details", 1000, 700);
        renderGrid(600);
        renderLabel("Λεπτομέρειες Πακέτου");
        renderPackageDetails(pkg, optionBtn);
    }

    private void renderPackageDetails(Package pkg, Button optionButton) {



        Text idText = new Text("ID: " + pkg.getPackageID());

        Text locationText = new Text("Location: " + pkg.getLocation());

        Text descriptionText = new Text("Description: " + pkg.getDescription());

        Text priceText = new Text("Price: " + pkg.getPrice());

        // Add the package details to the grid
        Text[] labels = {idText, locationText, descriptionText, priceText};

        optionButton.addEventHandler(ActionEvent.ACTION, event -> {
            this.stage.close();
        });

        Button[] buttons = {optionButton};
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
