package com.example.demo1;



public class MainScreen extends LayoutScreen {
    public MainScreen(){
        super("Κύρια Οθόνη");
        renderWelcomeLabel("Καλωσήρθατε στην εφαρμογή μας!");
        renderMainScreenMenu();
        renderContainer(700, 150);
        renderContainerLabel("Προτεινόμενος Προορισμός:Αφάλες Ιθάκης");
        renderMainContainerText();
        renderHorizontalBar();
    }
    private void renderMainScreenMenu(){
        String[] buttons = {"Εξερεύνηση Πακέτων","Εξόφληση Πακέτων","Τα Πακέτα Μου","Ακύρωση κράτησης"}; // τι ρυθμισεις ειναι αυτές;;;;
        renderButtonsInNavigation(buttons);
    }
    private void renderMainContainerText(){
        String instructionText = "Από το χωριό Πλατρειθιά, παίρνουμε το δρόμο για τις Αφάλες. "
                + "Διασχίζοντας μια ονειρική τοποθεσία με τροπική βλάστηση, φτάνει κανείς στη θαυμάσια παραλία του κόλπου των Αφαλών. "
                + "Πανέμορφος τόπος με χοντρά, άσπρα βότσαλα και βραχώδεις ακτές, που κρύβουν ακόμα δύο ερημικές παραλίες, το Κουλούμι και το Περιβόλι, "
                + "γι’αυτούς που αναζητούν την απόλυτη ηρεμία. Κοιτάζουν προς τον Βορρά και στον ορίζοντά τους έχουν την Λευκάδα. "
                + "Οι Αφάλες συνήθως έχουν από ελαφρύ έως έντονο κυματισμό, καθώς η παραλία «βλέπει» μαΐστρο";
        renderInstruction(instructionText);
    }

}
