package com.example.demo1;

public class QuarterPackageList extends ListScreen<QuarterGUI> {
    public QuarterPackageList() {
        super("Λίστα Ξενοδοχείων");
        renderListContent(QuarterGUI.getPackages());

    }
}
