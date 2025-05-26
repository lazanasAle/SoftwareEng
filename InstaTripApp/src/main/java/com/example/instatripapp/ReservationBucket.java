package com.example.instatripapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReservationBucket {
    private long PackageID;
    private String status="Σε αναμονή πληρωμής";
    //first Name of Customer in
    private String CustomerFirstName;
    private String CustomerLastName;

    //fixed Παρακαλω οι εκδρομες να παιρνουν πανω απο τρια ατομα

    private int People=3;
    private String location;
    private double price;
    private String AgentName;
    private String description;
    DataSourceManager manager;
    private long CustomerId;
    private long packid;


    private Package pack;

    public ReservationBucket(Package pack,DataSourceManager manager){
        this.pack=pack;
        price= pack.getPrice();
        AgentName= pack.getName();
        description= pack.getDescription();
        location= pack.location;
        packid= pack.getPackageID();
        this.manager=manager;
        GetCustomerName();

        String query = "insert into ReservationBucket (PackageId, CustomerId, people, description, price, location) VALUES (?,?,?,?,?,?);";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();

        try {
            if(db_con.isClosed()){
                manager.connect();
            }
            stmt = manager.getDb_con().prepareStatement(query);
            boolean inserted=manager.commit(stmt, new Object[]{packid,CustomerId,3,description,price,location});
            if(!inserted){
                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            }
        } catch (SQLException e) {

            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }

    }
    public void GetCustomerName(){
        Stage KeyPage=new Stage();

        Label title=new Label("Δωστε ονομα χρηστη");
        TextField insert=new TextField("FirstName");
        TextField insertS=new TextField("SecondName");
        Button submit=new Button("Submit");

        submit.setOnAction(e->{
            CustomerFirstName=insert.getText();
            CustomerLastName=insertS.getText();
            CustomerId=GetId();
            KeyPage.close();

        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title,insert,insertS,submit);

        Scene scene = new Scene(layout, 300, 200);

        KeyPage.setScene(scene);
        KeyPage.initModality(Modality.APPLICATION_MODAL); // Block other windows
        KeyPage.showAndWait();

    }

    private long GetId() {
        String query = "SELECT customerID from Customer where firstName=? and lastName =?";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try{

            var ret = manager.fetch(stmt, new Object[]{this.CustomerFirstName,this.CustomerLastName});
            Map<String,Object> row= ret.get(0);
            return (long)row.get("customerID");
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        return -1;

    }


}
