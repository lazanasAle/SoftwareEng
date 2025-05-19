package com.example.instatripapp;

import javafx.application.Application;
import javafx.stage.Stage;

//jdk 23 everyone

public class InstatripRun extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        DataSourceManager manager = new DataSourceManager();
        manager.connect();
        TourAgency agency = new TourAgency(1);
        agency.finalizePackage(manager);

        /*ExtPartner extPartner = new ExtPartner("ParisValianatos-Studios", manager);
        extPartner.SearchCooparation(manager);*/
    }
}