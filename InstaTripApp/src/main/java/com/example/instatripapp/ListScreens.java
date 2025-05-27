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
    DataSourceManager manager;

    public ResultScreen(List<Map<String, Object>> result,DataSourceManager manager) {
        super(result,manager,"Ενεργες εκδρομες στην περιοχη σας");
        this.manager=manager;
        renderPackageList(result,manager,"Ενεργες εκδρομες στην περιοχη σας");
        selectedPackages=ScreenRedirect.send(result);
        Button submit=new Button("Submit");

        grid.add(submit,1,10);

        submit.setOnAction(e->{
            selectedPackages=ScreenRedirect.send(result);
            PackageDetailsScreen pack=new PackageDetailsScreen(selectedPackages,manager,"Λεπτομέρειες Πακέτου");
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
    StringWrapper contents;
    DataSourceManager manager;
    Customer customer;

    public FilterScreen(StringWrapper contents, DataSourceManager manager, Customer client) {
        super("Φίλτρα", 700, 700);
        this.contents=contents;
        this.manager=manager;
        this.customer=client;
        renderGrid(500);
        renderLabel("Επιλέξτε τα φίλτρα που θέλετε να εφαρμόσετε:");
        renderFilterOptions();
    }
    private void renderFilterOptions() {
        Button Place=new Button("Περιοχη");
        Button Price=new Button("Τιμη");

        Place.setMaxWidth(Double.MAX_VALUE);
        Price.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHalignment(Place, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(Price, javafx.geometry.HPos.CENTER);

        Place.setOnAction(e->{
            takePlace();
        });
        Price.setOnAction(e->{
            takePrice();
        });


        grid.add(Place, 0, 1, 2, 1);
        grid.add(Price, 0, 2, 2, 1);

    }
    public void takePlace(){
        Stage KeyPage=new Stage();

        Label title=new Label("Εισαγεται την περιοχη");
        TextField insert=new TextField("Περιοχη");
        Button submit=new Button("Submit");

        submit.setOnAction(e->{
            String Place=insert.getText();
            try {
                FilterSearch filterSearch=new FilterSearch(Place,this.contents,manager,this.customer);

            } catch (NoSuchElementException ex) {
                ScreenRedirect.launchErrorMsg("Δεν υπαρχοθν αντικειμενα με αυτην την περιγραφη");
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
    public void takePrice(){
        Stage KeyPage=new Stage();

        Label title=new Label("Εισαγεται μεγιστη τιμη για πακετο");
        TextField insert=new TextField("Τιμη");
        Button submit=new Button("Submit");

        submit.setOnAction(e->{

            try {
                double Price=Double.parseDouble(insert.getText());
                FilterSearch filterSearch=new FilterSearch(Price,this.contents,manager,this.customer);
            }
            catch (NoSuchElementException ex) {
                ScreenRedirect.launchErrorMsg("Δεν υπαρχει αντικειμενο με αυτη την περιγραφη");
            }
            catch (NumberFormatException nme){
                ScreenRedirect.launchErrorMsg("Συμπληρώστε σωστά μια τιμή");
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
    DataSourceManager manager;
    String title;
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


    public PackageListScreen(List<Map<String, Object>> result,DataSourceManager manager,String title) {
        super("Λίστα Πακέτων", 1400, 950);
        renderGrid(900);
        this.manager=manager;
        this.title=title;
        renderPackageList(result,manager,title);
    }

    public void renderPackageList(List<Map<String, Object>> packageQueryResult,DataSourceManager manager,String title) {

        if(title.equals("Ενεργες εκδρομες στην περιοχη σας")){
            renderLabel(title);
            List<Package> separated = ScreenRedirect.send(packageQueryResult);
            try {
                List<String>columnNames = new ArrayList<>(packageQueryResult.getFirst().keySet());
                String[] cnamesArray = new String[columnNames.size()];
                columnNames.toArray(cnamesArray);
                renderArray(cnamesArray,separated, cnamesArray,"Αίτημα");
                Button keywords=new Button("Λεξεις Κλειδία");
                grid.add(keywords,0,10);
                keywords.setOnAction(e->keywords_commit(keywords,manager));
            }
            catch (NoSuchElementException nse){
                ScreenRedirect.launchErrorMsg("Δεν Υπάρχουν αντικείμενα με αυτή την περιγραφή");
                this.stage.close();
            }

        }
        else if (title.equals("Εμφανηση ενεργων πακετων για πελατη")) {
            renderLabel(title);
            List<Package> separated = ScreenRedirect.send(packageQueryResult);
            try {
                List<String> columnNames = new ArrayList<>(packageQueryResult.getFirst().keySet());
                String buttonName = "Καλαθι";
                String[] cnamesArray = new String[columnNames.size()];
                columnNames.toArray(cnamesArray);
                renderArray(cnamesArray, separated, cnamesArray, buttonName);
            }catch (NoSuchElementException nse){
                ScreenRedirect.launchErrorMsg("Δεν Υπάρχουν αντικείμενα με αυτή την περιγραφή");
                this.stage.close();
            }
        }
        else if (title.equals("Εμφανηση Πακετων καλαθιου")) {
            renderLabel(title);
            List<Package> separated = ScreenRedirect.send(packageQueryResult);
            try {
                List<String> columnNames = new ArrayList<>(packageQueryResult.getFirst().keySet());
                String buttonName = "Πληρωμη";
                String[] cnamesArray = new String[columnNames.size()];
                columnNames.toArray(cnamesArray);
                renderArray(cnamesArray, separated, cnamesArray, buttonName);
            }catch (NoSuchElementException nse){
                ScreenRedirect.launchErrorMsg("Δεν Υπάρχουν αντικείμενα με αυτή την περιγραφή");
                this.stage.close();
            }

        }

    }

    public void keywords_commit(Button ownerButton,DataSourceManager manager){

        Stage KeyPage=new Stage();

        Label title=new Label("Εισάγετε τις λέξεις κλειδιά");
        TextField insert=new TextField("keywords");
        Button submit=new Button("Submit");

        submit.setOnAction(e->{
            String keywords=insert.getText();
            try {
                ScreenConnector.keywords_transfer(keywords,manager);
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
    DataSourceManager manager;
    ComboBox<String> SuggestBox;
    public SuggestionScreen(String[] recommendedResults,DataSourceManager manager) {
        super("Προτεινόμενα αποτελέσματα", 600, 600);
        renderGrid(200);
        renderLabel("Mήπως εννοείτε:");
        this.manager=manager;
        suggestedWords=new String[1]; //θεμα με μεγεθος παιρνει ενα και δεν βγαζει exception
        getValues=new ArrayList<>();
        renderSuggestions(recommendedResults);
        renderSubmitButtons();
    }

    private void renderSuggestions(String[] recommendedResults) {
        SuggestBox = new ComboBox<>();
        SuggestBox.getItems().addAll(recommendedResults);
        SuggestBox.setValue(recommendedResults[0]);
        grid.add(SuggestBox, 0, 1, 2, 1);
        GridPane.setHalignment(SuggestBox, javafx.geometry.HPos.CENTER);
    }
    private void renderSubmitButtons(){
        Button submitButton = new Button("Υποβολή");

        submitButton.setOnAction(e->{
            String option=SuggestBox.getValue();
            ShowCorrectPack(option,manager);
        });

        Button cancelButton = new Button("Ακύρωση");
        grid.add(submitButton, 0, 2, 1, 1);
        grid.add(cancelButton, 1, 2, 1, 1);
        GridPane.setHalignment(submitButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(cancelButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
    public void ShowCorrectPack(String select,DataSourceManager manager){
        this.suggestedWords[0]= select;
        try {
            SearchContent s=new SearchContent(suggestedWords,manager);
        } catch (IOException e) {
           ScreenRedirect.launchErrorMsg("Σφάλμα Ι/Ο");
        }
    }
}

class RequestListScreen extends ListScreen<Request> {
    static ExtPartner extPartner;
    public RequestListScreen(List<Map<String, Object>> requests, PopupWindow popupWindow) {
        super("Λίστα Συνεργατών", 1000, 700);
        renderGrid(900);
        renderRequestList(requests, popupWindow);
    }

    private void renderRequestList(List<Map<String, Object>> requests, PopupWindow popupWindow) {
        List<Request> requestList = ScreenConnector.visualiseRequests(requests);
        try {
            List<String> columnNames = new ArrayList<>(requests.getFirst().keySet());
            String buttonName = "Λεπτομέρειες";
            String[] cnamesArray = new String[columnNames.size()];
            columnNames.toArray(cnamesArray);
            renderArray(cnamesArray, requestList, cnamesArray, buttonName, popupWindow);
        }catch (NoSuchElementException exe){
            ScreenRedirect.launchErrorMsg("Δεν υπάρχουν αντικείμενα με αυτή την περιγραφή");
        }

    }
    public RequestListScreen(List<Map<String, Object>> requests,String title,ExtPartner extPartner) {

        super("Λιστα αιτηματων για συνεργασια", 1000, 700);
        this.extPartner=extPartner;
        renderGrid(900);
        renderRequestList(requests,title);
    }

    private void renderRequestList(List<Map<String, Object>> requests, String title) {
        List<Request> requestList = ScreenConnector.sendReq(requests);
        if(title.equals("Προβολη για ακυρωση συνεργασιας")) {
            try {
                List<String> columnNames = new ArrayList<>(requests.getFirst().keySet());
                String buttonName = "Ακυρωση";
                String[] cnamesArray = new String[columnNames.size()];
                columnNames.toArray(cnamesArray);
                renderArray(cnamesArray, requestList, cnamesArray, buttonName);
            } catch (NoSuchElementException exe) {
                ScreenRedirect.launchErrorMsg("Δεν υπάρχουν αντικείμενα με αυτή την περιγραφή");
            }
        }
        else if (title.equals("Προβολη για τροποποιηση συνεργασιας")) {
            try {
                List<String> columnNames = new ArrayList<>(requests.getFirst().keySet());
                String buttonName = "Τροποποιηση";
                String[] cnamesArray = new String[columnNames.size()];
                columnNames.toArray(cnamesArray);
                renderArray(cnamesArray, requestList, cnamesArray, buttonName);
            } catch (NoSuchElementException exe) {
                ScreenRedirect.launchErrorMsg("Δεν υπάρχουν αντικείμενα με αυτή την περιγραφή");
            }

        }
        else if(title.equals("Προβολή για αποδοχή συνεργασίας")){
            List<String> columnNames = new ArrayList<>(requests.getFirst().keySet());
            String buttonName = "Αποδοχή";
            String[] cnamesArray = new String[columnNames.size()];
            columnNames.toArray(cnamesArray);
            renderArray(cnamesArray, requestList, cnamesArray, buttonName);
        }

    }
    public static ExtPartner getExt (){
        return extPartner;
    }
}