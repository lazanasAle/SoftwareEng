package com.example.instatravelgui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
// super class for all screens
public class Screen{
    protected GridPane grid;
    protected Stage stage;

    public Screen(){
        this.grid = new GridPane();
        this.stage = new Stage();
    }

    protected void renderStage(String title,int d1,int d2){
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setWidth(d1);
        stage.setHeight(d2);
        stage.show();
    }
    protected void renderGrid(){
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(20, 20, 20, 20)); // padding
         stage.setScene(new Scene(grid));


    }

}
