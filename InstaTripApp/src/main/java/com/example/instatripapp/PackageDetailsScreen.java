package com.example.instatripapp;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PackageDetailsScreen extends Screen {;
    private int gridPosition = 1;
    DataSourceManager manager;
    private String title;
    private static Package pack;

    public PackageDetailsScreen(Package pkg, Button optionBtn, DataSourceManager manager) {
        // screen methods
        super("Package Details", 1000, 700);
        renderGrid(600);
        renderLabel("Λεπτομέρειες Πακέτου");
        this.manager=manager;
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
        if (optionButton.getText().equals("Αποστολή")){
            optionButton.addEventHandler(ActionEvent.ACTION, event ->{
                PackageCoopForm pcoop = new PackageCoopForm(pkg, manager);
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


    public PackageDetailsScreen(List<Package> selectedPackages,DataSourceManager manager,String title) {
        // screen methods
        super("Package Details", 800, 700);
        renderGrid(600);
        this.title=title;
        renderLabel(title);
        this.manager=manager;
        renderPackageDetails(selectedPackages,title);
    }


    //its only for extpartner
    private void renderPackageDetails(List<Package> selectedPackages,String title) {
        if(title.equals("Λεπτομέρειες Πακέτου")) {
            pack = selectedPackages.get(0);
            Text TourName = new Text("Name: " + pack.getName());
            Text descriptionText = new Text("Description: " + pack.getDescription());
            Text startDate = new Text("The starting Day:" + pack.startDate);
            Text endDate = new Text("End Day is:" + pack.endDate);

            Text[] labels = {TourName, descriptionText, startDate, endDate};


            Button cooperationButton = new Button("Συνεργασία");
            Button[] buttons = {cooperationButton};
            addElementsToGrid(labels, buttons);

            cooperationButton.setOnAction(e -> {
                send_coop_suggestion_selelct(selectedPackages, manager);
            });
        }
        else if(title.equals("Λεπτομέρειες Πακέτου για τροποιηση")){
            pack = selectedPackages.get(0);
            Text TourName = new Text("Name: " + pack.getName());
            Text descriptionText = new Text("Description: " + pack.getDescription());
            Text startDate = new Text("The starting Day:" + pack.startDate);
            Text endDate = new Text("End Day is:" + pack.endDate);
            Text price = new Text("Price:" + pack.price);

            Text[] labels = {TourName, descriptionText, startDate, endDate,price};


            Button chengecoop = new Button("Tροποποιηση");
            Button[] buttons = {chengecoop};
            addElementsToGrid(labels, buttons);

            chengecoop.setOnAction(e -> {
                new_price_commit(selectedPackages, manager);
            });
        }
    }



    public void send_coop_suggestion_selelct(List<Package> selectedPackages,DataSourceManager manager){
        pack=selectedPackages.get(0);
        ScreenRedirect.create_coop_form_screen(pack,manager);

    }
    public void new_price_commit(List<Package> selectedPackages,DataSourceManager manager){
        Stage KeyPage=new Stage();

        Label title=new Label("Εισαγετε το ποσο χρηματων που επιθυμητε να λαβετε");
        TextField insert=new TextField("money");
        Button submit=new Button("Submit");

        submit.setOnAction(e->{

            try {
                Double money=Double.parseDouble(insert.getText());
                Double nowmoney=Double.parseDouble(String.valueOf(pack.price));
                Double total_money=money+nowmoney;

                boolean valid = ScreenConnector.check_bounds(Double.parseDouble(insert.getText()));
                if (valid) {
                    String q = "update Package set price=? where PackageID=?";
                    PreparedStatement stmt = null;
                    Connection db_con = manager.getDb_con();
                    try {
                        if (db_con.isClosed())
                            manager.connect();
                        stmt = manager.getDb_con().prepareStatement(q);
                        var ret = manager.commit(stmt, new Object[]{total_money, (Long) pack.getPackageId()});
                        if (!ret) {
                            ScreenRedirect.launchErrorMsg("Αποτυχια Τροποποιησης Λογω Βασης");
                        }
                        ScreenRedirect.launchSuccessMsg("Επιτυχια Τροποποιησης");
                    } catch (SQLException eXp) {
                        ScreenRedirect.launchErrorMsg("Αποτυχια Τροποποιησης Λογω Βασης");
                    }
                }
                else{
                    ScreenRedirect.launchErrorMsg("Αποτυχια Τροποποιησης");
                }
            }
            catch (NumberFormatException nme){
                ScreenRedirect.launchErrorMsg("Συμπληρώστε σωστά ένα ποσό");
            }
            catch (Exception ex) {
                ScreenRedirect.launchErrorMsg("Αποτυχια");
            }finally {
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
