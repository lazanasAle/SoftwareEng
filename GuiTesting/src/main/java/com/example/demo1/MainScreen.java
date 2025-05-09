package com.example.demo1;



public class MainScreen extends LayoutScreen {
    public MainScreen(){
        super("Κύρια Οθόνη");
        renderMainScreenMenu();
        renderContainerLabel("Προτεινόμενος Προορισμός:Αφάλες Ιθάκης");
        renderMainContainerText();
        setHyperText("Δείτε Περισσότερα:","https://www.ithaca.gr/archiki/endiaferonta-ke-drastiriotites/paralies-gr/afales-gr/");
    }
    private void renderMainScreenMenu(){
        String[] buttons = {"Συνεργασία","Προτάσεις","Προφίλ"}; // τι ρυθμισεις ειναι αυτές;;;;
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
