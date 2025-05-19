package com.example.demo1;

public class CooperationListScreen extends DataScreen<QuarterGUI> {
    public CooperationListScreen() {
        super("Λίστα Συνεργατών");
        renderListContent(QuarterGUI.getPackages());
        renderButtonsInNavigation(new String[]{"Αναζήτηση Ξενοδοχείου"});
        renderContainerLabel("Λίστα συνεργατών");
    }
}
