package com.example.instatripapp;

import java.util.List;


public class PackageOptionsScreen extends ListScreen {
    public PackageOptionsScreen() {
        super("Επιλογές Πακέτου", 800, 500);
        renderGrid(300);
        renderPackageOptions();
    }

    private void renderPackageOptions() {
        renderLabel("Επιλέξτε μια ενέργεια για το συγκεκριμμένο πακέτο:");
        List<String> options = List.of("Επιλογή Πακέτου", "Πληρωμή Πακέτου", "Ακύρωση Πακέτου", "Αξιολόγηση Πακέτου");
        renderList(options);
    }
}
