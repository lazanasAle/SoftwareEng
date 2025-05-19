package com.example.demo1;

public class ResultScreen extends ListScreen<PackageGUI> {
    public ResultScreen() {
        super("Αποτελέσματα Αναζήτησης Πακέτων");
        renderContainerLabel("Αποτελέσματα Αναζήτησης Πακέτων");
        renderListContent(PackageGUI.getPackages());
    }

}
