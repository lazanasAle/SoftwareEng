package com.example.demo1;

public class PaidPackageList extends ListScreen<PackageGUI> {
    public PaidPackageList() {
        super("Λίστα Πληρωμένων Πακέτων");
        renderContainerLabel("Πληρωμένα Πακέτων");
        renderListContent(PackageGUI.getPackages());
    }

}
