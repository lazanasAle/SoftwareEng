package com.example.instatripapp;
import javafx.scene.Node;
import javafx.util.Pair;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ScreenRedirect {
    public static void getCreatePackageScreen(TourAgency agency, DataSourceManager manager){
        CreatePackageForm newPackageForm = new CreatePackageForm(agency, manager);
    }
    public static void getPackageMenu(Package refered, DataSourceManager manager) throws SQLException {
        String Message = "Τοποθεσία: "+refered.location+"\n";
        Message += "Μεγιστο πλήθος εκδρομέων: "+refered.maxParticipants+"\n";
        Message += "Τιμή Εκδρομής: "+refered.price+"\n";
        Message += "Hμερομηνία Έναρξης: "+refered.startDate+"\n";
        Message += "Ημερομηνία Επιστροφής: "+refered.endDate+"\n";
        ConfirmScreen confirmPackageCreation = new ConfirmScreen("Επιβεβαίωση Δημιουργίας Πακέτου", Message, (Integer res)->{
            if(res==1){
                ScreenConnector.InsertPackage(refered, manager);
            }
            else {
                new CreatePackageForm(refered.organizer, manager);
            }
        });


    }

    public static void launchPackageListScreen(List<Map<String, Object>> elements, TourAgency agency, PopupWindow pwindow){
        PackageListScreen packageListScreen = new PackageListScreen(elements, agency, pwindow);

    }

    public static void launchErrorMsg(String Mesg) {
        ErrorMessage errorScreen = new ErrorMessage(Mesg);
    }
}


class ScreenConnector{
    public static void InsertPackage(Package refered, DataSourceManager manager){
        String query = "INSERT INTO Package (startDate, endDate, location, price, status, AgencyID, maxParticipants) VALUES (?, ?, ?, ?, 'Σε εξέλιξη', ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = manager.getDb_con().prepareStatement(query);
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try {
            boolean inserted=manager.commit(stmt, new Object[]{refered.startDate, refered.endDate, refered.location, refered.price, refered.organizer.key, refered.maxParticipants});
            if(!inserted){
                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            }
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
    }
    public static List<Package> visualisePackages(List<Map<String, Object>> packageQueryResult, TourAgency organizer){
        List<Package> selectedPackages = new ArrayList<>();
        for(Map<String, Object> row : packageQueryResult){
            Long package_id = (Long) row.get("PackageID");
            Date start = (Date) row.get("startDate");
            LocalDate startDate = start.toLocalDate();
            Date end = (Date) row.get("endDate");
            LocalDate endDate = end.toLocalDate();
            String location = String.valueOf(row.get("location"));
            Double price = (Double) row.get("price");
            String statusString = String.valueOf(row.get("status"));
            voyageStatus status = voyageStatus.fromString(statusString);
            Long agencyID = (Long) row.get("AgencyID");
            Long maxParticipants = (Long) row.get("maxParticipants");
            Package newVoyage = new Package(organizer, null, null);
            newVoyage.initializePackage(location, price, maxParticipants, status, startDate, endDate, package_id);
            selectedPackages.add(newVoyage);
        }
         return selectedPackages;
    }

    public static List<Map<String, Object>> takePackages(TourAgency agency, DataSourceManager manager){
        String query = "SELECT PackageID, startDate, endDate, location, price, status, AgencyID, maxParticipants FROM Package WHERE AgencyID=?;";
        PreparedStatement stmt = null;
        try {
            stmt=manager.getDb_con().prepareStatement(query);
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try{
            var ret = manager.fetch(stmt, new Integer[]{agency.key});
            return ret;
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        return null;
    }
}
