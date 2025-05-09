package com.example.instatripapp;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//jdk 23 everyone

public class InstatripRun extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String[] recommendedResults = {"Ιθάκη","Θιάκη","Θράκη"};
        SuggestionScreen suggestionScreen = new SuggestionScreen(recommendedResults);
        PaymentScreen pScreen = new PaymentScreen();
    }
}