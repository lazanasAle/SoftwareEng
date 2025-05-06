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
        CooperationListScreen loginPage = new CooperationListScreen();

    }
}