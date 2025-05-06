package com.example.instatripapp;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.List;

// Strongly recommend to do this class into 2 classes
// One is ArrayScreen and the other is ListScreen
// Recommendation: is to put ListScreen to be a superclass to PackageDetailsScreen, PaymentScreen,FilterScreen,

class HistoryListScreen extends ListScreen {
    public HistoryListScreen() {
        super("Ιστορικό Πακέτων", 600, 600);
        renderGrid(500);
        renderLabel("Η Λίστα ιστορικού πακέτων σας");
        renderHistoryList();
    }

    private void renderHistoryList() {
        PackageGUI[] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Λεπτομέρειες";
        renderArray(columnNames, java.util.Arrays.asList(packages), propertyNames, buttonName);
    }
}
class ResultScreen extends ListScreen {
    public ResultScreen() {
        super("Αποτελέσματα", 600, 600);
        renderGrid(500);
        renderResultList();
    }

    private void renderResultList() {
        renderLabel("Αποτελέσματα Πακέτων");
        // Sample data for packages
        PackageGUI[] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };

        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Επιλογή Πακέτου";
        renderArray(columnNames, Arrays.asList(packages), propertyNames,buttonName);
    }
}
class PackageOptionsScreen extends ListScreen {
    public PackageOptionsScreen() {
        super("Επιλογές Πακέτου", 800, 500);
        renderGrid(300);
        renderPackageOptions();
    }

    private void renderPackageOptions() {
        renderLabel("Επιλέξτε μια ενέργεια για το συγκεκριμμένο πακέτο:");
        List<String> options = List.of("Επιλογή Πακέτου", "Πληρωμή Πακέτου", "Ακύρωση Πακέτου", "Αξιολόγηση Πακέτου");
        renderList(options);
    }
}
class FilterScreen extends ListScreen {
    public FilterScreen() {
        super("Φίλτρα", 700, 700);
        renderGrid(500);
        renderLabel("Επιλέξτε τα φίλτρα που θέλετε να εφαρμόσετε:");
        renderFilterOptions();
    }
    private void renderFilterOptions() {

        // Dummy data for demonstration
        List<String> filterOptions = List.of("Φίλτρο 1", "Φίλτρο 2", "Φίλτρο 3");

        renderList(filterOptions);
    }
}
class QuarterListScreen extends ListScreen{
    public QuarterListScreen() {
        super("Λίστα Καταλυμάτων", 700, 700);
        renderGrid(500);
        renderQuarterList();
    }

    private void renderQuarterList() {
        renderLabel("Επιλέξτε κατάλυμα:");

        String[] columnNames = {"Apartment Name", "Region", "Apartment Type"};
        String[] propertyNames = {"apartmentName", "region", "apartmentType"};
        String buttonName = "Select";

        // Dummy data for demonstration
        List<QuarterGUI> quarters = List.of(
                new QuarterGUI("Apartment A", "Region 1", "Type 1"),
                new QuarterGUI("Apartment B", "Region 2", "Type 2"),
                new QuarterGUI("Apartment C", "Region 3", "Type 3")
        );

        renderArray(columnNames, quarters, propertyNames, buttonName);
    }

}
class PaidPackageList extends ListScreen{
    public PaidPackageList() {
        super("Λίστα Πακέτων", 1000, 1000);
        renderGrid(700);
        renderPackageList();
    }

    private void renderPackageList() {
        renderLabel("Η Λίστα πληρωμένων πακέτων σας");
        PackageGUI [] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Κριτική";
        renderArray(columnNames, Arrays.asList(packages), propertyNames,buttonName);
    }
}
class PackageListScreen extends ListScreen {
    public PackageListScreen() {
        super("Λίστα Πακέτων", 600, 600);
        renderGrid(500);
        renderPackageList();
    }

    private void renderPackageList() {
        renderLabel("Η Λίστα πακέτων σας");
        PackageGUI[] packages = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Επιλογή";
        renderArray(columnNames, Arrays.asList(packages), propertyNames, buttonName);
    }
}
class SuggestionScreen extends ListScreen {
    public SuggestionScreen() {
        super("Προτεινόμενα αποτελέσματα", 600, 600);
        renderGrid(500);
        renderSuggestions();
    }

    private void renderSuggestions() {
        renderLabel("Αποτελέσματα αναζήτησης για εσάς");

        // Dummy data for demonstration
        PackageGUI [] suggestions = {
                new PackageGUI("1", "Πακέτο 1", "Περιγραφή 1", "100€"),
                new PackageGUI("2", "Πακέτο 2", "Περιγραφή 2", "200€"),
                new PackageGUI("3", "Πακέτο 3", "Περιγραφή 3", "300€")
        };
        String[] columnNames = {"ID", "Όνομα Πακέτου", "Περιγραφή", "Τιμή"};
        String[] propertyNames = {"id", "name", "description", "price"};
        String buttonName = "Επιλογή";

        renderArray(columnNames, Arrays.asList(suggestions), propertyNames, buttonName);
    }
}
class CooperationListScreen extends ListScreen {
    public CooperationListScreen() {
        super("Λίστα Συνεργατών", 700, 700);
        renderGrid(500);
        renderCooperationList();
    }

    private void renderCooperationList() {
        renderLabel("Η Λίστα συνεργατών σας");
        QuarterGUI[] quarters = {
                new QuarterGUI("Διαμέρισμα 1", "Αθήνα", "Διαμέρισμα"),
                new QuarterGUI("Διαμέρισμα 2", "Θεσσαλονίκη", "Διαμέρισμα"),
                new QuarterGUI("Διαμέρισμα 3", "Πάτρα", "Διαμέρισμα")
        };
        String[] columnNames = {"Όνομα Διαμερίσματος", "Περιοχή", "Τύπος Διαμερίσματος"};
        String[] propertyNames = {"apartmentName", "region", "apartmentType"};
        String buttonName = "Επιλογή";
        renderArray(columnNames, Arrays.asList(quarters), propertyNames, buttonName);
    }
}