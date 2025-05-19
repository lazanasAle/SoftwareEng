package com.example.demo1;


public class MyPackageListScreen extends DataScreen<PackageGUI>{
    public MyPackageListScreen() {
        super("Αναζήτηση Πακέτων");
        renderButtonsInNavigation(new String[]{"Εκ νέου Αναζήτηση"});
        renderContainerLabel("Αποτελέσματα Αναζήτησης Πακέτων");
        renderListContent(PackageGUI.getPackages());

    }


}
