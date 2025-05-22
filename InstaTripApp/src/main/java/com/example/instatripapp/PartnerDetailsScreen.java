package com.example.instatripapp;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PartnerDetailsScreen extends Screen{

    private int gridPosition = 1;

    public PartnerDetailsScreen(ExtPartner part, Button optionBtn) {
        // screen methods
        super("Package Details", 1000, 700);
        renderGrid(600);
        renderLabel("Λεπτομέρειες Πακέτου");
        renderPartnerDetails(part, optionBtn);
    }

    private void renderPartnerDetails(ExtPartner part, Button optionButton) {



        Text idText = new Text("ID: " + part.getPartnerID());

        Text locationText = new Text("Location: " + part.getLocation());

        Text descriptionText = new Text("Description: " + part.getDescription());


        // Add the package details to the grid
        Text[] labels = {idText, locationText, descriptionText};

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
