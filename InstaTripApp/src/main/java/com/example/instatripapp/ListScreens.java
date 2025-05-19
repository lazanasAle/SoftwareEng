package com.example.instatripapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
class ResultScreen extends PackageListScreen {
    private List<Package> selectedPackages;

    public ResultScreen(List<Map<String, Object>> result) {
        super(result);

        renderPackageList(result);
        selectedPackages=ScreenRedirect.send(result);
        Button submit=new Button("Submit");

        grid.add(submit,1,10);

        submit.setOnAction(e->{
            selectedPackages=ScreenRedirect.send(result);
            PackageDetailsScreen pack=new PackageDetailsScreen(selectedPackages);
        });

    }




    /*private void renderResultList() {
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
    }*/
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


    public PackageListScreen(List<Map<String, Object>> result) {
        super("Λίστα Πακέτων", 1000, 950);
        renderGrid(900);
        renderPackageList(result);
    }

    public void renderPackageList(List<Map<String, Object>> packageQueryResult) {
        renderLabel("Ενεργες εκδρομες στην περιοχη σας");
        List<Package> separated = ScreenRedirect.send(packageQueryResult);

        List<String>columnNames = new ArrayList<>(packageQueryResult.getFirst().keySet());
        String buttonName = "Αιτήμα";
        String[] cnamesArray = new String[columnNames.size()];
        columnNames.toArray(cnamesArray);
        renderArray(cnamesArray,separated, cnamesArray,buttonName);



        Button keywords=new Button("Λεξεις Κλειδία");



        grid.add(keywords,0,10);

        keywords.setOnAction(e->keywords_commit(keywords));


    }

    public void keywords_commit(Button ownerButton){

        Stage KeyPage=new Stage();

        Label title=new Label("Εισαγεται τις λεξεις κλειδια");
        TextField insert=new TextField("keywords");
        Button submit=new Button("Submit");

        submit.setOnAction(e->{
            String keywords=insert.getText();
            try {
                ScreenConnector.keywords_transfer(keywords);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            finally{
                KeyPage.close();
            }
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title,insert,submit);

        Scene scene = new Scene(layout, 300, 200);

        KeyPage.setScene(scene);
        KeyPage.initModality(Modality.APPLICATION_MODAL); // Block other windows
        KeyPage.showAndWait();

    }
}


// no more ListScreen
class SuggestionScreen extends Screen{
    private String[] suggestedWords;
    private ArrayList<String> getValues;
    public SuggestionScreen(String[] recommendedResults) {
        super("Προτεινόμενα αποτελέσματα", 600, 600);
        renderGrid(200);
        renderLabel("Mήπως εννοείτε:");
        suggestedWords=new String[1]; //θεμα με μεγεθος παιρνει ενα και δεν βγαζει exception
        getValues=new ArrayList<>();
        String select=renderSuggestions(recommendedResults);
        renderSubmitButtons(select);
    }

    private String renderSuggestions(String[] recommendedResults) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(recommendedResults);
        comboBox.setValue(recommendedResults[0]);
        String select=comboBox.getValue();
        grid.add(comboBox, 0, 1, 2, 1);
        GridPane.setHalignment(comboBox, javafx.geometry.HPos.CENTER);
        return select;
    }
    private void renderSubmitButtons(String select){
        Button submitButton = new Button("Υποβολή");

        submitButton.setOnAction(e->{
            ShowCorrectPack(select);
        });

        Button cancelButton = new Button("Ακύρωση");
        grid.add(submitButton, 0, 2, 1, 1);
        grid.add(cancelButton, 1, 2, 1, 1);
        GridPane.setHalignment(submitButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(cancelButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
    public void ShowCorrectPack(String select){
        this.suggestedWords[0]= select;
        System.out.println("Selected"+select);
        try {
            SearchContent s=new SearchContent(suggestedWords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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