package com.example.instatripapp;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.*;

// Strongly recommend to do this class into 2 classes
// One is ArrayScreen and the other is ListScreen
// Recommendation: is to put ListScreen to be a superclass to PackageDetailsScreen, PaymentScreen,FilterScreen,

class HistoryListScreen extends ListScreen {
    public HistoryListScreen() {
        super("Ιστορικό Πακέτων", 600, 600);
        renderGrid(500);
        renderLabel("Η Λίστα ιστορικού πακέτων σας");
        renderHistoryList();
    }

    private void renderHistoryList() {
        PackageGUI[] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Λεπτομέρειες";
        renderArray(columnNames, java.util.Arrays.asList(packages), propertyNames, buttonName, null);
    }
}
class ResultScreen extends ListScreen {
    public ResultScreen() {
        super("Αποτελέσματα", 600, 600);
        renderGrid(500);
        renderResultList();
    }

    private void renderResultList() {
        renderLabel("Αποτελέσματα Πακέτων");
        // Sample data for packages
        PackageGUI[] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };

        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Επιλογή Πακέτου";
        renderArray(columnNames, Arrays.asList(packages), propertyNames,buttonName, null);
    }
}
class PackageOptionsScreen extends ListScreen {
    public PackageOptionsScreen() {
        super("Επιλογές Πακέτου", 800, 500);
        renderGrid(300);
        renderPackageOptions();
    }

    private void renderPackageOptions() {
        renderLabel("Επιλέξτε μια ενέργεια για το συγκεκριμμένο πακέτο:");
        List<String> options = List.of("Επιλογή Πακέτου", "Πληρωμή Πακέτου", "Ακύρωση Πακέτου", "Αξιολόγηση Πακέτου");
        renderList(options);
    }
}
class FilterScreen extends ListScreen {
    public FilterScreen() {
        super("Φίλτρα", 700, 700);
        renderGrid(500);
        renderLabel("Επιλέξτε τα φίλτρα που θέλετε να εφαρμόσετε:");
        renderFilterOptions();
    }
    private void renderFilterOptions() {

        // Dummy data for demonstration
        List<String> filterOptions = List.of("Φίλτρο 1", "Φίλτρο 2", "Φίλτρο 3");

        renderList(filterOptions);
    }
}

class PartnerListScreen extends ListScreen<ExtPartner>{
    public PartnerListScreen(List<Map<String, Object>> partnerQueryResult, Package refered, PopupWindow pwindow) {
        super("Λίστα Συνεργατών", 1000, 950);
        renderGrid(900);
        renderPartnerList(partnerQueryResult, refered, pwindow);
    }

    private void renderPartnerList(List<Map<String, Object>> partnerQueryResult, Package refered, PopupWindow pwindow) {
        renderLabel("Βρείτε πιθανούς εξωτερικούς συνεργάτες");
        List<ExtPartner> selectedPartners = ScreenConnector.visualisePartners(partnerQueryResult, refered);
        try {
            List<String> columnNames = new ArrayList<>(partnerQueryResult.getFirst().keySet());
            columnNames.remove("description");
            columnNames.remove("schedule");
            columnNames.remove("address");
            String buttonName = "Λεπτομέρειες";
            String[] cnamesArray = new String[columnNames.size()];
            columnNames.toArray(cnamesArray);
            renderArray(cnamesArray, selectedPartners, cnamesArray, buttonName, pwindow);
        }catch (NoSuchElementException exe){
            ScreenRedirect.launchErrorMsg("Δεν υπάρχουν αντικείμενα με αυτή την περιγραφή");
        }
    }

}

class PaidPackageList extends ListScreen{
    public PaidPackageList() {
        super("Λίστα Πακέτων", 1000, 1000);
        renderGrid(700);
        renderPackageList();
    }

    private void renderPackageList() {
        renderLabel("Η Λίστα πληρωμένων πακέτων σας");
        PackageGUI [] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Κριτική";
        renderArray(columnNames, Arrays.asList(packages), propertyNames,buttonName, null);
    }
}
class PackageListScreen extends ListScreen<Package> {

    public PackageListScreen(List<Map<String, Object>> packageQueryResult, TourAgency organizer, PopupWindow popupWindow) {
        super("Λίστα Πακέτων", 1000, 950);
        renderGrid(900);
        renderPackageList(packageQueryResult, organizer, popupWindow);
    }

    private void renderPackageList(List<Map<String, Object>> packageQueryResult, TourAgency organizer, PopupWindow pwindow) {
        renderLabel("Η Λίστα πακέτων σας");
        List<Package> selectedPackages = ScreenConnector.visualisePackages(packageQueryResult, organizer);
        try {
            List<String> columnNames = new ArrayList<>(packageQueryResult.getFirst().keySet());
            columnNames.remove("description");
            String buttonName = "Επιλογή";
            String[] cnamesArray = new String[columnNames.size()];
            columnNames.toArray(cnamesArray);
            renderArray(cnamesArray, selectedPackages, cnamesArray, buttonName, pwindow);
        }catch (NoSuchElementException exe){
            ScreenRedirect.launchErrorMsg("Δεν υπάρχουν αντικείμενα με αυτή την περιγραφή");
        }
    }
}


// no more ListScreen
class SuggestionScreen extends Screen{
    public SuggestionScreen(String[] recommendedResults) {
        super("Προτεινόμενα αποτελέσματα", 600, 600);
        renderGrid(200);
        renderLabel("Mήπως εννοείτε:");
        renderSuggestions(recommendedResults);
        renderSubmitButtons();
    }

    private void renderSuggestions(String[] recommendedResults) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(recommendedResults);
        comboBox.setValue(recommendedResults[0]);
        grid.add(comboBox, 0, 1, 2, 1);
        GridPane.setHalignment(comboBox, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
    private void renderSubmitButtons(){
        Button submitButton = new Button("Υποβολή");
        Button cancelButton = new Button("Ακύρωση");
        grid.add(submitButton, 0, 2, 1, 1);
        grid.add(cancelButton, 1, 2, 1, 1);
        GridPane.setHalignment(submitButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(cancelButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
}
class CooperationListScreen extends ListScreen {
    public CooperationListScreen() {
        super("Λίστα Συνεργατών", 700, 700);
        renderGrid(500);
        renderCooperationList();
    }

    private void renderCooperationList() {
        renderLabel("Η Λίστα συνεργατών σας");
        QuarterGUI[] quarters = {
                new QuarterGUI("Διαμέρισμα 1", "Αθήνα", "Διαμέρισμα"),
                new QuarterGUI("Διαμέρισμα 2", "Θεσσαλονίκη", "Διαμέρισμα"),
                new QuarterGUI("Διαμέρισμα 3", "Πάτρα", "Διαμέρισμα")
        };
        String[] columnNames = {"Όνομα Διαμερίσματος", "Περιοχή", "Τύπος Διαμερίσματος"};
        String[] propertyNames = {"apartmentName", "region", "apartmentType"};
        String buttonName = "Επιλογή";
        renderArray(columnNames, Arrays.asList(quarters), propertyNames, buttonName, null);
    }
}