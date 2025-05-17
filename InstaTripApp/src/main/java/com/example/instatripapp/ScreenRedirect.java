package com.example.instatripapp;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;


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

    public static void launchSearchScreen(SearchContent content, TourAgency organizer, DataSourceManager manager){
        SearchPackageScreen searchPackageScreen = new SearchPackageScreen(content, organizer, manager);
    }

    public static void launchErrorMsg(String Mesg) {
        ErrorMessage errorScreen = new ErrorMessage(Mesg);
    }
}


class ScreenConnector{
    public static void InsertPackage(Package refered, DataSourceManager manager){
        String query = "INSERT INTO Package (startDate, endDate, location, description, price, status, AgencyID, maxParticipants) VALUES (?, ?, ?, ?, ?, 'Σε εξέλιξη', ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = manager.getDb_con().prepareStatement(query);
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try {
            boolean inserted=manager.commit(stmt, new Object[]{refered.startDate, refered.endDate, refered.location, refered.getDescription(), refered.price, refered.organizer.key, refered.maxParticipants});
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
            String description = String.valueOf(row.get("description"));
            Double price = (Double) row.get("price");
            String statusString = String.valueOf(row.get("status"));
            voyageStatus status = voyageStatus.fromString(statusString);
            Long agencyID = (Long) row.get("AgencyID");
            Long maxParticipants = (Long) row.get("maxParticipants");
            Package newVoyage = new Package(organizer, null, null);
            newVoyage.initializePackage(location, price, maxParticipants, status, startDate, endDate, package_id, description);
            selectedPackages.add(newVoyage);
        }
         return selectedPackages;
    }

    public static List<Map<String, Object>> takePackages(TourAgency agency, DataSourceManager manager, SearchContent cntnt){
        String query = "SELECT PackageID, startDate, endDate, location, price, description, status, AgencyID, maxParticipants FROM Package WHERE AgencyID=? AND (description LIKE ? OR location LIKE ?);";
        PreparedStatement stmt = null;
        try {
            stmt=manager.getDb_con().prepareStatement(query);
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try{
            String desc="%"+cntnt.content+"%";
            var ret = manager.fetch(stmt, new Object[]{agency.key, desc, desc});
            return ret;
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        return null;
    }

    public static void changeStatus(long key, DataSourceManager manager, String NewStatus){
        String query = "UPDATE Package SET status=? WHERE PackageID=?;";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try {
            boolean updated = manager.commit(stmt, new Object[]{NewStatus, key});
            if(!updated){
                ScreenRedirect.launchErrorMsg(manager.errMesg);
            }
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ: "+e.getMessage());
        }
    }

    public static void afterSearchPerform(TourAgency organizerMember, DataSourceManager manager, SearchContent content){
        List<Map<String, Object>> packages = ScreenConnector.takePackages(organizerMember, manager, content);
        ScreenRedirect.launchPackageListScreen(packages, organizerMember, new PopupWindow() {
            @Override
            public void createPopup(Object element, Node anchor, long keySearch) {
                Stage popupStage = new Stage();
                popupStage.initStyle(StageStyle.UNDECORATED);
                popupStage.initModality(Modality.WINDOW_MODAL);
                popupStage.initOwner(anchor.getScene().getWindow());

                Button detailsBtn = new Button("Λεπτομέρειες");
                Button cancelBtn = new Button("Ακύρωση");
                detailsBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                Button closeBtn = new Button("X");
                cancelBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                closeBtn.setOnAction(e -> popupStage.close());

                cancelBtn.setOnAction(e -> {
                    ScreenConnector.changeStatus(keySearch, manager, "Ακυρωμένο");
                    popupStage.close();
                });
                detailsBtn.setOnAction(e -> {
                    popupStage.close();
                    if (element instanceof Package) {
                        Package packageElement = (Package) element;
                        Button optionBtn = new Button("Οριστικοποίηση");
                        optionBtn.setOnAction((event) -> {
                            ScreenConnector.changeStatus(keySearch, manager, "Ενεργοποιημένο");
                        });
                        popupStage.close();
                        PackageDetailsScreen pds = new PackageDetailsScreen(packageElement, optionBtn);
                    }
                });
                HBox header = new HBox(closeBtn);
                header.setAlignment(Pos.TOP_RIGHT);

                VBox layout = new VBox(10, header, detailsBtn, cancelBtn);
                layout.setPadding(new Insets(10));
                layout.setStyle("-fx-background-color: white; -fx-border-color: gray;");

                popupStage.setScene(new Scene(layout));

                Bounds bounds = anchor.localToScreen(anchor.getBoundsInLocal());
                popupStage.setX(bounds.getMinX());
                popupStage.setY(bounds.getMaxY());

                popupStage.show();
            }
        });
    }
}


