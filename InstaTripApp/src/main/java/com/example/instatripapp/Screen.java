package com.example.instatripapp;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

// super class for all screens
public class Screen{
    protected GridPane grid;
    protected Stage stage;
    protected final int widthOfScreen;
    protected final int heightOfScreen;
    protected final String screenTitle;

    public Screen(String title,int widthOfScreen, int heightOfScreen) {
        this.widthOfScreen = widthOfScreen;
        this.heightOfScreen = heightOfScreen;
        this.screenTitle = title;
        this.grid = new GridPane();
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

    protected void renderGrid(int sizeOfContainer) {
        StackPane root = new StackPane();

        grid.setAlignment(Pos.CENTER); // center contents inside grid
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setId("grid-box");
        grid.setMaxWidth(sizeOfContainer);
        grid.setMaxHeight(sizeOfContainer);


        // Add grid to root
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root, widthOfScreen, heightOfScreen);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        stage.setScene(scene);
    }

    protected void renderLabel(String label) {
        Text l = new Text(label);
        l.setId("welcome_label");
        grid.add(l, 0, 0 ,2,1); // Span 4 columns
        GridPane.setHalignment(l, HPos.CENTER); // Center horizontally
    }

}
