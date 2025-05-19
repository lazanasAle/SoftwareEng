package com.example.demo1;

public class PaidPackageList extends DataScreen<PackageGUI>{
    public PaidPackageList() {
        super("Λίστα Πληρωμένων Πακέτων");
        renderContainerLabel("Πληρωμένα Πακέτων");
        renderListContent(PackageGUI.getPackages());
    }

}
