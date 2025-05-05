package com.example.instatripapp;

import java.util.Arrays;
// see comments in ListScreen.java
public class MainScreen extends ListScreen {
    public MainScreen() {
        // screen methods
        super("Κύριο Μενού", 650, 500);
        renderGrid(300);
        renderLabel("Καλώς ήρθατε στο InstaTrip κύριε Μανωλάτο!");
        renderMenu();
    }

    private void renderMenu() {
        String[] menuOptions = {
                "Επιλογή Πακέτου",
                "Ιστορικό Πακέτων",
                "Φίλτρα",
                "Συνεργάτες",
                "Αξιολόγηση Πακέτου",
                "Πληρωμή Πακέτου"
        };
        renderList(Arrays.asList(menuOptions));
    }

}
