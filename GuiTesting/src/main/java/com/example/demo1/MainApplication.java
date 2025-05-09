package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PackageListScreen mainScreen = new PackageListScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}