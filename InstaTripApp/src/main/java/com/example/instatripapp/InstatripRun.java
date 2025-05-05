package com.example.instatripapp;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

//jdk 23 everyone

public class InstatripRun extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene menu = new Scene(new MainScreen(), 1400, 900);
        menu.getStylesheets().add("MainPage.css");
        primaryStage.setScene(menu);
        primaryStage.show();

    }
}