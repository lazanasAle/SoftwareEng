package com.example.instatravelgui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
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
        renderGrid();
        renderStage();
    }

    private void renderStage(){
        stage.setTitle(screenTitle);
        stage.setResizable(false);
        stage.setWidth(widthOfScreen);
        stage.setHeight(heightOfScreen);
        stage.show();
    }

    private void renderGrid(){
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(20, 20, 20, 20)); // padding
         stage.setScene(new Scene(grid));
    }

    protected void renderLabel(String label) {
        Text l = new Text(label);
        l.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        l.setFill(Color.web("#000000"));
        l.setStyle("-fx-font-size: 20px; -fx-fill: black;");
        grid.add(l, 0, 0);
        GridPane.setHalignment(l, javafx.geometry.HPos.CENTER); // Center horizontally
        GridPane.setValignment(l, javafx.geometry.VPos.CENTER); // Center vertically
    }

}
