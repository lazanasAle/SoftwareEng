package com.example.instatrip_java_impl;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class Screen {

}

class ListScreen{

}

class ErrorMessage{
    private Text error;
    Button ok;
    //maybe an icon
    public ErrorMessage(){
        error=new Text("Error has been made");
        ok=new Button("OK");
    }
}

//new class
class LoginScreen extends VBox{
    private Text header;
    TextField password;
    TextField username;
    GridPane container;
    Label user;
    Label pass;
    Button Login;
    //App logo

    //create the scene
    public LoginScreen(){

        container =new GridPane();
        container.setAlignment(Pos.CENTER);
        container.setHgap(10);
        container.setVgap(10);


        header=new Text("LoginPage");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        user=new Label("Username:");

        pass=new Label("Password:");

        username=new TextField();
        password=new TextField();

        Login=new Button("Login");
        Login.setAlignment(Pos.BOTTOM_CENTER);
        Login.setOnAction(this::Authorization);

        container.add(header,0,0,2,1);
        container.add(user,0,1);
        container.add(username,1,1);
        container.add(pass,0,2);
        container.add(password,1,2);
        container.add(Login,1,4);

        this.getChildren().add(container);

    }

    //login button calls for authorization maybe its action listener
    public void Authorization(ActionEvent e){
        System.out.println("It works");
    }



}

class MainScreen{

}


