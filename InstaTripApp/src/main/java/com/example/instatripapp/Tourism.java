package com.example.instatripapp;

import java.lang.constant.Constable;
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
        SearchContent cntnt = new SearchContent("");
        ScreenRedirect.launchPackageSearchScreen(cntnt, this, manager);
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