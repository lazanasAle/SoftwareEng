package com.example.instatripapp;

import java.util.List;

public class QuarterListScreen extends ListScreen{
    public QuarterListScreen() {
        super("Λίστα Καταλυμάτων", 700, 700);
        renderGrid(500);
        renderQuarterList();
    }

    private void renderQuarterList() {
        renderLabel("Επιλέξτε κατάλυμα:");

        String[] columnNames = {"Apartment Name", "Region", "Apartment Type"};
        String[] propertyNames = {"apartmentName", "region", "apartmentType"};
        String buttonName = "Select";

        // Dummy data for demonstration
        List<QuarterGUI> quarters = List.of(
                new QuarterGUI("Apartment A", "Region 1", "Type 1"),
                new QuarterGUI("Apartment B", "Region 2", "Type 2"),
                new QuarterGUI("Apartment C", "Region 3", "Type 3")
        );

        renderArray(columnNames, quarters, propertyNames, buttonName);
    }

}
