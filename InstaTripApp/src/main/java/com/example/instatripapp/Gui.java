package com.example.instatripapp;


import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.*;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class Screen extends VBox{

}

class ListScreen extends Screen{

}

class ErrorMessage extends Screen{
    private Text error;
    Button ok;
    //maybe an icon
    public ErrorMessage(){
        error=new Text("Error has been made");
        ok=new Button("OK");
    }
}

//new class
class LoginScreen extends Screen{

    private TextField password;
    private TextField username;
    private GridPane container;

    private Button Login;
    private Label header;




    //create the scene
    public LoginScreen() {

        this.setAlignment(Pos.CENTER);

        container = new GridPane();
        container.setAlignment(Pos.CENTER);
        container.setId("container");
        container.setHgap(50);
        container.setVgap(50);
        container.setMaxWidth(500);
        container.setPrefWidth(500);
        container.setPrefHeight(1000);

        header=new Label("Please confirm your info");
        header.setId("header");



        username = new TextField();
        username.setPromptText("Username:");
        username.setId("username");


        password = new TextField();
        password.setPromptText("Password:");
        password.setId("password");

        Login = new Button("Login");
        Login.setAlignment(Pos.BOTTOM_CENTER);
        Login.setOnAction(this::Authorization);
        Login.setId("login");


        container.add(username, 0, 3);

        container.add(password, 0, 6);
        container.add(Login, 0, 10);


        this.getChildren().addAll(header,container);

    }

    //login button calls for authorization maybe its action listener
    public void Authorization(ActionEvent e){
        System.out.println("It works");
    }



}

class MainScreen extends Screen{

}

