package com.example.demo1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.IntStream;

public class DataScreen<T extends DisplayableItem> extends LayoutScreen{
    public DataScreen(String screenName) {
        super(screenName);
        renderButtonsInNavigation(new String[]{"Εκ νέου Αναζήτηση"});
        renderContainer(800, 500);
        renderHorizontalBar();
    }
    // Credits: Paris

    protected void renderListContent(T[] listOfObjects) {
        VBox listBox = new VBox(10);
        listBox.setPadding(new Insets(20, 0, 0, 0));

        IntStream.rangeClosed(0, listOfObjects.length-1).forEach(i -> listBox.getChildren().add(createCard(listOfObjects[i])));

        container.add(listBox, 0, 2);
    }
    private HBox createCard(T object) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.getStyleClass().add("card");
        card.setMinWidth(600);
        card.setAlignment(Pos.CENTER_LEFT);

        ImageView imageView = new ImageView(); // παιδια αφηνω αυτο εδω ειναι για την ακρη της καθε γραμμης στο ui να βαλουμε φωτο
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        imageView.getStyleClass().add("card-image-placeholder");

        VBox textBox = new VBox(5);
        Label titleLabel = new Label(object.getName());
        titleLabel.getStyleClass().add("card-title");
        Label description = new Label(object.getDescription());
        description.getStyleClass().add("card-paragraph");
        description.setWrapText(true);
        description.setMaxWidth(400);
        textBox.getChildren().addAll(titleLabel, description);

        VBox priceAndStars = new VBox(5);
        priceAndStars.setAlignment(Pos.CENTER_RIGHT);
        Label priceLabel = new Label(object.getPrice() + "€");

        HBox starsBox = new HBox(2);
        for (int i = 0; i < 5; i++) {
            Label star = new Label("★");
            star.setStyle(i < 3 ? "-fx-text-fill: black;" : "-fx-text-fill: #ccc;");
            priceLabel.setStyle("-fx-font-size: 26px; -fx-font-family: 'Manrope';");
            starsBox.setStyle("-fx-font-size: 30px; -fx-font-family: 'Manrope'; -fx-background-color: transparent; -fx-border-color: transparent;");
            starsBox.getChildren().add(star);
        }

        priceAndStars.getChildren().addAll(priceLabel, starsBox);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        card.getChildren().addAll(imageView, textBox, spacer, priceAndStars);
        return card;
    }
}
