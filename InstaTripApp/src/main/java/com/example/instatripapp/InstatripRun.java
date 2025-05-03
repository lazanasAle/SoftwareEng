package com.example.instatripapp;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

//jdk 21 everyone

public class InstatripRun extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene login = new Scene(new LoginScreen(), 1400, 900);
        login.getStylesheets().add("login.css");
        primaryStage.setScene(login);
        primaryStage.show();

    }
}