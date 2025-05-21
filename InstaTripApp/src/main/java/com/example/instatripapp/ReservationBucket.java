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

    

    private Package pack;

    public ReservationBucket(Package pack){
        this.pack=pack;
        price= pack.getPrice();
        AgentName= pack.getName();
        description= pack.getDescription();
        location= pack.location;
        GetCustomerName();

        System.out.println("Price: " + price);
        System.out.println("Agent Name: " + AgentName);
        System.out.println("Description: " + description);
        System.out.println("Location: " + location);
        System.out.println("CustomerName: " + CustomerFirstName);
        System.out.println("CustomerName: " + CustomerLastName);


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




}
