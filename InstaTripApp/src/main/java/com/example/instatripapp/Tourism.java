package com.example.instatripapp;

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

    public int key;

    public TourAgency(int key){
        this.key=key;
    }


    public void createPackage(DataSourceManager manager){
        ScreenRedirect.getCreatePackageScreen(this, manager);
    }

    public void finalizePackage(DataSourceManager manager){
        StringWrapper cntnt = new StringWrapper("");
        ScreenRedirect.launchPackageSearchScreen(cntnt, this, manager);
    }
}

class Program{
    // To be filled with more attributes and functions
}





class Participation {
    //public final TourAgency organizer; //Βγαζει error
    public ExtPartner partner;
    public LivingQuarter shelter;
    private DataSourceManager manager;
    private long ExtPartnerID;
    private String Status;
    private long PackID;




    Participation(TourAgency agency, ExtPartner partner){
        //organizer=agency;
        this.partner=partner;
    }

    Participation(long ExtPartnerID,String Status,long PackID){
        this.ExtPartnerID=ExtPartnerID;
        this.Status=Status;
        this.PackID=PackID;
        SaveToDb();
    }

    public void SaveToDb() {
        String query = "insert into partnerPackage (partnerID,packageID,status) values (?,?,?)";

        PreparedStatement stmt = null;
        manager=new DataSourceManager();

        Connection db_con = manager.getDb_con();

        try {
            manager.connect();
            stmt = manager.getDb_con().prepareStatement(query);
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try {
            boolean inserted=manager.commit(stmt, new Object[]{this.ExtPartnerID,this.PackID,this.Status});
            if(!inserted){
                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            }
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }

    }

    // To be filled with more attributes and functions

}