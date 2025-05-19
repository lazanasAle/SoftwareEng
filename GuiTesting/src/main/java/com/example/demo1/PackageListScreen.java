//package com.example.demo1;
//
//import javafx.geometry.HPos;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//
//import java.util.Objects;
//
//public class PackageListScreen extends Screen {
//
//    public PackageListScreen() {
//        super("Αναζήτηση Πακέτων");
//        container = new GridPane();
//        container.setPadding(new Insets(30));
//        container.setHgap(20);
//        container.setVgap(15);
//        container.setAlignment(Pos.TOP_LEFT);
//
//        ColumnConstraints col1 = new ColumnConstraints();
//        col1.setPercentWidth(100);
//        container.getColumnConstraints().add(col1);
//
//        renderListContent();
//
//        Scene scene = new Scene(container, widthOfScreen, heightOfScreen);
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style_for_package_list.css")).toExternalForm());
//        stage.setScene(scene);
//    }
//
//    private void renderListContent() {
//        Label title = new Label("Αναζήτηση Πακέτων");
//        title.getStyleClass().add("listTitle");
//        container.add(title, 0, 0);
//        GridPane.setHalignment(title, HPos.LEFT);
//
//        Label subtitle = new Label("Εμφανίστηκαν ΧΧ αποτελέσματα");
//        subtitle.getStyleClass().add("listSubtitle");
//        container.add(subtitle, 0, 1);
//        GridPane.setHalignment(subtitle, HPos.LEFT);
//
//        VBox listBox = new VBox(10);
//        listBox.setPadding(new Insets(20, 0, 0, 0));
//
//        for (int i = 1; i <= 4; i++) {
//            listBox.getChildren().add(createPackageCard("Πακέτο " + i));
//        }
//
//        container.add(listBox, 0, 2);
//    }
//
//    private HBox createPackageCard(String title) {
//        HBox card = new HBox(15);
//        card.setPadding(new Insets(15));
//        card.getStyleClass().add("card");
//        card.setAlignment(Pos.CENTER_LEFT);
//
//        ImageView imageView = new ImageView(); // παιδια αφηνω αυτο εδω ειναι για την ακρη της καθε γραμμης στο ui να βαλουμε φωτο
//        imageView.setFitWidth(60);
//        imageView.setFitHeight(60);
//        imageView.getStyleClass().add("card-image-placeholder");
//
//        VBox textBox = new VBox(5);
//        Label titleLabel = new Label(title);
//        titleLabel.getStyleClass().add("card-title");
//        Label description = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit...");
//        description.getStyleClass().add("card-paragraph");
//        description.setWrapText(true);
//        description.setMaxWidth(400);
//        textBox.getChildren().addAll(titleLabel, description);
//
//        VBox priceAndStars = new VBox(5);
//        priceAndStars.setAlignment(Pos.CENTER_RIGHT);
//        Label priceLabel = new Label("XXX.XX€");
//
//        HBox starsBox = new HBox(2);
//        for (int i = 0; i < 5; i++) {
//            Label star = new Label("★");
//            star.setStyle(i < 3 ? "-fx-text-fill: black;" : "-fx-text-fill: #ccc;");
//            priceLabel.setStyle("-fx-font-size: 26px; -fx-font-family: 'Manrope';");
//            starsBox.setStyle("-fx-font-size: 30px; -fx-font-family: 'Manrope'; -fx-background-color: transparent; -fx-border-color: transparent;");
//            starsBox.getChildren().add(star);
//        }
//
//        priceAndStars.getChildren().addAll(priceLabel, starsBox);
//
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//
//        card.getChildren().addAll(imageView, textBox, spacer, priceAndStars);
//        return card;
//    }
//}
//
