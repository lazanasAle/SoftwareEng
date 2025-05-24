package com.example.instatripapp;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.List;

public class PackageDetailsScreen extends Screen {;
    private int gridPosition = 1;
    DataSourceManager manager;

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

        Text People = new Text("Reserved People: " + pkg.getPeople());

        // Add the package details to the grid
        Text[] labels = {idText, locationText, descriptionText, priceText};
        if (optionButton.getText().equals("Submit")) {
            optionButton.addEventHandler(ActionEvent.ACTION, event -> {

                ScreenRedirect.launchReservationForm(pkg, manager);
            });
        }
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

    private static Package pack;
    public PackageDetailsScreen(List<Package> selectedPackages,DataSourceManager manager) {
        // screen methods
        super("Package Details", 800, 700);
        renderGrid(600);
        renderLabel("Λεπτομέρειες Πακέτου");
        this.manager=manager;
        renderPackageDetails(selectedPackages);
    }


    //its only for extpartner
    private void renderPackageDetails(List<Package> selectedPackages) {
        pack=selectedPackages.get(0);
        Text TourName = new Text("Name: " + pack.getName());
        Text descriptionText = new Text("Description: " + pack.getDescription());
        Text startDate=new Text("The starting Day:"+pack.startDate);
        Text endDate=new Text("End Day is:"+pack.endDate);
        // Add the package details to the grid
        Text[] labels = {TourName, descriptionText, startDate,endDate};

        //Button backButton = new Button("Πληρωμή");
        Button cooperationButton = new Button("Συνεργασία");
        Button[] buttons = {cooperationButton};
        addElementsToGrid(labels, buttons);

        cooperationButton.setOnAction(e->{
            send_coop_suggestion_selelct(selectedPackages,manager);
        });
    }

    public void send_coop_suggestion_selelct(List<Package> selectedPackages,DataSourceManager manager){
        pack=selectedPackages.get(0);
        ScreenRedirect.create_coop_form_screen(pack,manager);

    }

}
