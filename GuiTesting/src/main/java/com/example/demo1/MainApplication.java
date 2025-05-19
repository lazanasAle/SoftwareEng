package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.smartcardio.Card;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CardScreen cardScreen = new CardScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}