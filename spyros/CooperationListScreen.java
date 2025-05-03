package com.example.instatravelgui;

import java.util.*;

public class CooperationListScreen extends ListScreen {
    public CooperationListScreen() {
        super("Λίστα Συνεργατών", 600, 300);
        renderCooperationList();
    }

    private void renderCooperationList() {
        renderLabel("Η Λίστα συνεργατών σας");
        QuarterGUI[] quarters = {
                new QuarterGUI("Διαμέρισμα 1", "Αθήνα", "Διαμέρισμα"),
                new QuarterGUI("Διαμέρισμα 2", "Θεσσαλονίκη", "Διαμέρισμα"),
                new QuarterGUI("Διαμέρισμα 3", "Πάτρα", "Διαμέρισμα")
        };
        String[] columnNames = {"Όνομα Διαμερίσματος", "Περιοχή", "Τύπος Διαμερίσματος"};
        String[] propertyNames = {"apartmentName", "region", "apartmentType"};
        String buttonName = "Επιλογή";
        renderArray(columnNames, Arrays.asList(quarters), propertyNames, buttonName);
    }
}
