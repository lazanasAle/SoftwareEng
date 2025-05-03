package com.example.instatravelgui;

import java.util.List;


public class PackageOptionsScreen extends ListScreen {
    public PackageOptionsScreen() {
        super("Επιλογές Πακέτου", 600, 300);
        renderPackageOptions();
    }

    private void renderPackageOptions() {
        renderLabel("Επιλέξτε μια ενέργεια για το συγκεκριμμένο πακέτο:");

        String[] columnNames = {"Επιλογή"}; // Column names for the list
        List<String> options = List.of("Επιλογή Πακέτου", "Πληρωμή Πακέτου", "Ακύρωση Πακέτου", "Αξιολόγηση Πακέτου");
        String[] propertyNames = {"value"}; // Property name for the list items
        String buttonName = "Ενέργεια"; // Button label for each row

        renderList(options);
    }
}
