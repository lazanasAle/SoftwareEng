package com.example.demo1;

public class HistoryListScreen extends DataScreen<PackageGUI>{
    public HistoryListScreen() {
        super("Ιστορικό Πακέτων");
        renderContainerLabel("Αποτελέσματα Ιστορικού Πακέτων");
        renderListContent(PackageGUI.getPackages());
    }
}
