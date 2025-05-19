package com.example.demo1;

public class HistoryListScreen extends ListScreen<PackageGUI> {
    public HistoryListScreen() {
        super("Ιστορικό Πακέτων");
        renderContainerLabel("Αποτελέσματα Ιστορικού Πακέτων");
        renderListContent(PackageGUI.getPackages());
    }
}
