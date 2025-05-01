package com.example.instatrip_java_impl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InstatripRun extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene login = new Scene(new LoginScreen(), 700, 600);
        login.getStylesheets().add("login.css");
        primaryStage.setScene(login);
        primaryStage.show();

    }
}