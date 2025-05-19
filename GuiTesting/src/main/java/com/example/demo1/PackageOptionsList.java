package com.example.demo1;

public class PackageOptionsList extends LayoutScreen{
    public PackageOptionsList() {
        super("Κύρια Οθόνη");
        renderWelcomeLabel("Επιλογές Πακέτου:");
        renderPackageOptionsList();
        renderHorizontalBar();
        renderContainer(700, 500);
        renderContainerLabel("Πακέτο 1 χαρακτηριστικά:");
        renderMainContainerText();
    }
    private void renderPackageOptionsList() {
        String[] buttons = {"Οριστικοποίηση Πακέτου","Προσθήκη Συνεργασιών"};
        renderButtonsInNavigation(buttons);
    }
    private void renderMainContainerText() {
        String sampleTextAboutPackage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        renderInstruction(sampleTextAboutPackage);
    }
}
