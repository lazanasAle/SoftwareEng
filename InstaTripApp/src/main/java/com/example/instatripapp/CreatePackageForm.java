package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreatePackageForm extends FormScreen{

    public CreatePackageForm(TourAgency organizer, DataSourceManager manager) {
        super("Δημιουργία Πακέτου", 700, 500);
        renderGrid(200);
        renderLabel("Συμπληρώστε τα στοιχεία του πακέτου σας");
        renderPackageForm(organizer, manager);
    }

    private void renderPackageForm(TourAgency organizer, DataSourceManager manager) {
        Package newVoyage = new Package(organizer, null, null);
        Label regionLabel = new Label("Γεωγραφική Περιοχή:");
        TextField regionTextArea = new TextField();
        Label availablePeopleLabel = new Label("Μέγιστο Πλήθος Εκδρομέων:");
        TextField availablePeopleTextArea = new TextField();
        Label priceLabel = new Label("Εκτιμώμενη Τιμή:");
        TextField priceTextArea = new TextField();
        Label startDateLabel = new Label("Ημερομηνία Έναρξης");
        DatePicker startDateTextAea = new DatePicker();
        Label endDateLabel = new Label("Ημερομηνία Επιστροφής");
        DatePicker endDateTextArea=new DatePicker();
        Label imageLabel = new Label("Εικόνες για ταξίδια:");
        Button uploadButton = new Button("Upload");


        // renderForm Details
        renderFormElements(
            new Label[]{regionLabel, availablePeopleLabel, priceLabel},
            new TextField[]{regionTextArea, availablePeopleTextArea, priceTextArea}, new DatePicker[]{startDateTextAea, endDateTextArea}, new Label[] {startDateLabel, endDateLabel});
        renderFormButtons(new Label[]{imageLabel}, new Button[]{uploadButton});

        submitButton.setOnAction(e->{
            createPackageListener(regionTextArea, priceTextArea, availablePeopleTextArea, startDateTextAea, endDateTextArea, newVoyage, manager);
        });
    }

    private void createPackageListener(TextField regionTextArea, TextField priceTextArea, TextField availablePeopleTextArea, DatePicker startDateTextAea, DatePicker endDateTextArea, Package newVoyage, DataSourceManager manager){
            var region = regionTextArea.getText();
            var priceString = priceTextArea.getText();
            var peopleString = availablePeopleTextArea.getText();
            var startDate = startDateTextAea.getValue();
            var endDate = endDateTextArea.getValue();
            boolean condition = region==null || region.isEmpty() || priceString==null || priceString.isEmpty() || peopleString==null || peopleString.isEmpty() || startDate==null || endDate==null;
            if(condition){
                ScreenRedirect.launchErrorMsg("Συμπληρώστε όλα τα πεδία της φόρμας");
                return;
            }
            try {
                if(startDate.isAfter(LocalDate.now()) && endDate.isAfter(startDate)) {
                    newVoyage.initializePackage(region, Double.parseDouble(priceString), Integer.parseInt(peopleString), voyageStatus.saved, startDate, endDate);
                    stage.close();
                    ScreenRedirect.getPackageMenu(newVoyage, manager);
                }
                else{
                    ScreenRedirect.launchErrorMsg("Συμπληρώστε Σωστά τα πεδία της φόρμας");
                }
            }catch (NumberFormatException eme){
                ScreenRedirect.launchErrorMsg("Συμπληρώστε Σωστά τα πεδία της φόρμας");
            }catch (SQLException sqe){
                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            }


    }
}
