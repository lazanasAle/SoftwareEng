package com.example.instatripapp;

public class HistoryListScreen extends ListScreen {
    public HistoryListScreen() {
        super("Ιστορικό Πακέτων", 600, 600);
        renderGrid(500);
        renderLabel("Η Λίστα ιστορικού πακέτων σας");
        renderHistoryList();
    }

    private void renderHistoryList() {
        PackageGUI[] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Λεπτομέρειες";
        renderArray(columnNames, java.util.Arrays.asList(packages), propertyNames, buttonName);
    }
}
