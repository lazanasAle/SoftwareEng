package com.example.instatripapp;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.constant.Constable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


enum voyageStatus{
    saved,
    active,
    canceled,
    finished;

    public static voyageStatus fromString(String dbValue){
        Map<String, voyageStatus> db_code_enum_mapper = Map.of(
                "Ολοκληρωμένο", finished,
                "Ακυρωμένο", canceled,
                "Ενεργοποιημένο", active,
                "Σε εξέλιξη", saved
        );

        return db_code_enum_mapper.get(dbValue);
    }

    public static String toString(voyageStatus statusValue){
        Map<? extends Constable, String> db_code_enum_mapper = Map.of(
                finished,"Ολοκληρωμένο",
                canceled,"Ακυρωμένο",
                active,"Ενεργοποιημένο",
                saved,  "Σε εξέλιξη"
        );
        return db_code_enum_mapper.get(statusValue);
    }

};

class TourAgency{

    public int key;

    public TourAgency(int key){
        this.key=key;
    }


    public void createPackage(DataSourceManager manager){
        ScreenRedirect.getCreatePackageScreen(this, manager);
    }

    public void finalizePackage(DataSourceManager manager) {
        List<Map<String, Object>> packages=ScreenConnector.takePackages(this, manager);
        ScreenRedirect.launchPackageListScreen(packages, this, new PopupWindow() {
            @Override
            public void createPopup(Object element, Node anchor, long keySearch) {
                Stage popupStage = new Stage();
                popupStage.initStyle(StageStyle.UNDECORATED);
                popupStage.initModality(Modality.WINDOW_MODAL);
                popupStage.initOwner(anchor.getScene().getWindow());

                Button activateBtn = new Button("Ενεργοποίηση");
                Button cancelBtn = new Button("Ακύρωση");
                activateBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                Button closeBtn = new Button("X");
                cancelBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                closeBtn.setOnAction(e -> popupStage.close());

                cancelBtn.setOnAction(e->{
                    ScreenConnector.changeStatus(keySearch, manager, "Ακυρωμένο");
                });
                activateBtn.setOnAction(e->{
                    ScreenConnector.changeStatus(keySearch, manager, "Ενεργοποιημένο");
                });
                HBox header = new HBox(closeBtn);
                header.setAlignment(Pos.TOP_RIGHT);

                VBox layout = new VBox(10, header, activateBtn, cancelBtn);
                layout.setPadding(new Insets(10));
                layout.setStyle("-fx-background-color: white; -fx-border-color: gray;");

                popupStage.setScene(new Scene(layout));

                Bounds bounds = anchor.localToScreen(anchor.getBoundsInLocal());
                popupStage.setX(bounds.getMinX());
                popupStage.setY(bounds.getMaxY());

                popupStage.show();
            }
        });
    }
}

class Program{
    // To be filled with more attributes and functions
}





class Participation {
    public final TourAgency organizer;
    public ExtPartner partner;
    public LivingQuarter shelter;

    // To be filled with more attributes and functions

    Participation(TourAgency agency, ExtPartner partner, LivingQuarter shelter){
        organizer=agency;
        this.partner=partner;
        this.shelter=shelter;
    }

    // To be filled with more attributes and functions

}