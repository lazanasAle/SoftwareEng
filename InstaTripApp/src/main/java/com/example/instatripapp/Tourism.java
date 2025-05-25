package com.example.instatripapp;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.lang.constant.Constable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public long key;

    public TourAgency(long key){
        this.key= key;
    }


    public void createPackage(DataSourceManager manager){
        ScreenRedirect.getCreatePackageScreen(this, manager);
    }

    public void finalizePackage(DataSourceManager manager){
        StringWrapper cntnt = new StringWrapper("");
        ScreenRedirect.launchPackageSearchScreen(cntnt, this, manager);
    }

    public void replyToRequest(DataSourceManager manager){
        List<Map<String, Object>> requests = ScreenConnector.takeRequests(this, manager);
        ScreenRedirect.launchRequestListScreen(requests, new PopupWindow<>() {
            @Override
            public void createPopup(Object element, Node anchor, long keySearch) {
                Button cancelButton = new Button("Απόρριψη");
                partnerType partner_type;
                if(element instanceof Request){
                    Request req = (Request) element;
                    partner_type=partnerType.fromString(req.getPtype());
                } else {
                    partner_type = partnerType.other;
                }
                cancelButton.setOnAction(event -> {
                    ScreenConnector.changeRequestStatus(keySearch, manager, "Ακυρωμένη", partner_type);
                });
                cancelButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
                Button approveButton = new Button("Αποδοχή");
                approveButton.setOnAction(event -> {
                    ScreenConnector.changeRequestStatus(keySearch, manager, "Σε ισχύ", partner_type);
                });
                approveButton.setStyle("-fx-background-color: green; -fx-text-fill: white");
                ScreenRedirect.createPopup(element, anchor, new Button[]{approveButton, cancelButton});
            }
        });
    }
}

class Program{
    // To be filled with more attributes and functions
}





class Participation {
    //public final TourAgency organizer;
    public ExtPartner partner;
    public LivingQuarter shelter;
    private DataSourceManager manager;
    private long ExtPartnerID;
    private String Status;
    private long PackID;
    private String type;
    private partnerType modtype;



    Participation(TourAgency agency, ExtPartner partner){
        //organizer=agency;
        this.partner=partner;
    }

    Participation(long ExtPartnerID, String Status, long PackID, String type){
        this.ExtPartnerID=ExtPartnerID;
        this.Status=Status;
        this.PackID=PackID;
        this.type=type;
        this.modtype=partnerType.fromString(type);
        SaveToDb();
    }

    public void SaveToDb() {
        String query;
        if(modtype==partnerType.quarter){
            query = "insert into quarterPackage (quarterID ,packageID,status) values (?,?,?)";
        }
        else{
            query = "insert into partnerPackage (partnerID,packageID,status) values (?,?,?)";
        }

        PreparedStatement stmt = null;
        manager=new DataSourceManager();

        Connection db_con = manager.getDb_con();

        try {
            manager.connect();
            stmt = manager.getDb_con().prepareStatement(query);
            boolean inserted=manager.commit(stmt, new Object[]{this.ExtPartnerID,this.PackID,this.Status});
            if(!inserted){
                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ 2");
            }
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ 1");
        }


    }

    // To be filled with more attributes and functions

}