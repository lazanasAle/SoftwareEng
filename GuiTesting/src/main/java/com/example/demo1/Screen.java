package com.example.demo1;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.util.Objects;
import java.util.Stack;

public class Screen {
    protected Stage stage;
    protected final int widthOfScreen=1272; // paris was 1920
    protected final int heightOfScreen=900; // paris was 1080
    private final String screenTitle;
    public Screen(String title) {

        this.screenTitle = title;
        this.stage = new Stage();
        renderStage();
    }
    private void renderStage(){
        stage.setTitle(screenTitle);
        stage.setResizable(false);
        stage.setWidth(widthOfScreen);
        stage.setHeight(heightOfScreen);
        stage.show();
    }

}
