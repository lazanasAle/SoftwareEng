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

public class Screen {
    protected GridPane container;
    protected Stage stage;
    protected final int widthOfScreen=1272;
    protected final int heightOfScreen=720;
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

    protected void renderContainerLabel(String text){
        Label label = new Label(text);
        label.getStyleClass().add("containerLabel");
        container.add(label, 0, 0,2,1);
        GridPane.setHalignment(label, HPos.CENTER); // Center the label in the grid cell
    }
    protected void renderInstruction(String text){
        Text instruction = new Text(text);
        instruction.getStyleClass().add("instructionText");
        instruction.setWrappingWidth(600); // Set the maximum width for the text
        container.add(instruction, 0, 1,2,1);
        GridPane.setHalignment(instruction, HPos.CENTER); // Center the label in the grid cell
    }
    protected void setHyperText(String text, String url) {
        Button hyperlink = new Button(text);
        hyperlink.getStyleClass().add("hyperlink");
        hyperlink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        container.add(hyperlink, 0,2,2,1);
    }
}
