package com.example.instatripapp;

import javafx.event.ActionEvent;
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
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.instatripapp.RequestListScreen.getExt;


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

    public static void launchPackageSearchScreen(StringWrapper content, TourAgency organizer, DataSourceManager manager){
        SearchPackageScreen searchPackageScreen = new SearchPackageScreen(content, organizer, manager);
    }

    public static void launchPartnerSearchScreen(StringWrapper content, Package pkg, DataSourceManager manager){
        SearchPartnerScreen searchPartnerScreen = new SearchPartnerScreen(content, pkg, manager);
    }

    public static void launchErrorMsg(String Mesg) {
        ErrorMessage errorScreen = new ErrorMessage(Mesg);
    }

    public static void launchPartnerListScreen(List<Map<String, Object>> elements, Package pkg, PopupWindow pwindow){
        PartnerListScreen partnerListScreen = new PartnerListScreen(elements, pkg, pwindow);
    }

    public static void createPopup(Object element, Node anchor, Button[] actionButtons) {
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(anchor.getScene().getWindow());

        Button closeBtn = new Button("X");
        closeBtn.setOnAction(e -> popupStage.close());

        for( Button btn : actionButtons){
            btn.addEventHandler(ActionEvent.ACTION, (event)->{
                popupStage.close();
            });
        }

        HBox header = new HBox(closeBtn);
        header.setAlignment(Pos.TOP_RIGHT);

        VBox layout = new VBox(10);
        layout.getChildren().add(header);
        layout.getChildren().addAll(actionButtons);
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: white; -fx-border-color: gray;");

        popupStage.setScene(new Scene(layout));

        Bounds bounds = anchor.localToScreen(anchor.getBoundsInLocal());
        popupStage.setX(bounds.getMinX());
        popupStage.setY(bounds.getMaxY());

        popupStage.show();
    }

    public static List<Package> send(List<Map<String, Object>> results){
        List<Package> selectedPackages = new ArrayList<>();
        for(Map<String, Object> row : results){

            String email = String.valueOf(row.get("email"));
            String name = String.valueOf(row.get("name"));

            Date start = (Date) row.get("startDate");
            LocalDate startDate = null;
            if (start != null) {
                startDate = start.toLocalDate();
            } else {
                startDate=LocalDate.now();
            }

            Date end = (Date) row.get("endDate");
            LocalDate endDate = null;
            if (end != null) {
                endDate = end.toLocalDate();
            }
            String description=String.valueOf(row.get("description"));

            Long maxParticipantsObj = (Long) row.get("maxParticipants");
            long maxParticipants = (maxParticipantsObj != null) ? maxParticipantsObj : 0;

            long PackID;
            double Price;
            String Location;
            Long customerId;
            int people;
            voyageStatus status=voyageStatus.fromString(String.valueOf(row.get("status")));

            if((Long)row.get("CustomerId")!=null){
                customerId=(Long)row.get("CustomerId");
            }
            else {customerId=null;}

            long custId = (customerId!= null) ? customerId.longValue() : -1L;

            if(row.get("people")!=null)  people=(int)row.get("people");
            else people=-1;

            Object pkgId = row.get("PackageID");
            if (pkgId == null) pkgId = row.get("PackageId");
            if (pkgId == null) pkgId = row.get("ReservationBucket.PackageId");

            PackID = (pkgId != null) ? (Long) pkgId : -1L;


            if(row.get("price")!=null){
                Price=Double.parseDouble(String.valueOf(row.get("price")));
            }
            else{Price=-1;}

            Location=String.valueOf(row.get("location"));


            Package newVoyage = new Package();
            newVoyage.initializePackage(PackID,endDate,name,description,email,startDate,maxParticipants,Location,Price,status,custId,people);
            selectedPackages.add(newVoyage);
        }
        return selectedPackages;


    }
    public static void make_result_screen(List<Map<String, Object>> keywords, DataSourceManager manager){
        ResultScreen packageListScreen=new ResultScreen(keywords,manager);

    }
    public static void launchSuggestionScreen(List<String> suggestions,DataSourceManager manager){
        String suggest[] = new String[suggestions.size()];
        for(int i=0;i<suggestions.size();i++){
            suggest[i]= suggestions.get(i);
        }
        SuggestionScreen suggestionScreen=new SuggestionScreen(suggest,manager);
    }

    public static void create_coop_form_screen(Package pack,DataSourceManager manager) {
        PackageCoopForm packageCoopForm=new PackageCoopForm(pack,manager);
    }

    public static void GetToMain(String typeuser, String username,DataSourceManager manager,long key) {
        MainScreen client=new MainScreen(typeuser,username,manager,key);

    }

    public static void launchPartnerDetailsScreen(ExtPartner partner, Button optionButton){
        PartnerDetailsScreen partnerDetailsScreen = new PartnerDetailsScreen(partner, optionButton);
    }
    public static void launchSearchScreen(StringWrapper cntnt, DataSourceManager manager,Customer customer) {

        SearchPackageScreen searchScreen=new SearchPackageScreen(cntnt,customer,manager);
    }
    public static void launchPackageListScreen(DataSourceManager manager,List<Map<String, Object>> result,Customer customer,String title){

        PackageListScreen packageListScreen=new PackageListScreen(result,manager,title);
    }

    public static void launchRequestListScreen(List<Map<String, Object>> elements, PopupWindow popupWindow){
        RequestListScreen requestListScreen = new RequestListScreen(elements, popupWindow);
    }

    public static void launchFilterScreen(Customer client, DataSourceManager manager, StringWrapper content) {
        FilterScreen filter=new FilterScreen(content,manager,client);
    }

    public static void launchReservationForm(Package pkg, DataSourceManager manager) {
        ReservationFormScreen reservationFormScreen=new ReservationFormScreen(pkg,manager);
    }

    public static void launchRequestListScreenEXT(List<Map<String, Object>> elements, String title, ExtPartner extPartner){
        RequestListScreen requestListScreen = new RequestListScreen(elements,title,extPartner);
    }

    public static void launchSuccessMsg(String mess) {
        SuccessMsg succ =new SuccessMsg(mess);
    }

    public static void show_cancelation_form(Request element,DataSourceManager manager) {
        CancelationForm cancelationForm=new CancelationForm(element,manager);
    }
}


class ScreenConnector{
    public static void InsertPackage(Package refered, DataSourceManager manager){
        String query = "INSERT INTO Package (startDate, endDate, location, description, price, status, AgencyID, maxParticipants) VALUES (?, ?, ?, ?, ?, 'Σε εξέλιξη', ?, ?);";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();

        try {
            if(db_con.isClosed()){
                manager.connect();
            }
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

    public static List<Map<String, Object>> takePackages(TourAgency agency, DataSourceManager manager, StringWrapper strwrapper){
        String query = "SELECT PackageID, startDate, endDate, location, price, description, status, AgencyID, maxParticipants FROM Package WHERE AgencyID=? AND (description LIKE ? OR location LIKE ?);";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try{
            String desc="%"+strwrapper.content+"%";
            var ret = manager.fetch(stmt, new Object[]{agency.key, desc, desc});
            return ret;
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        return null;
    }

    public static void changePackageStatus(long key, DataSourceManager manager, String NewStatus){
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

    public static void changeRequestStatus(long key, DataSourceManager manager, String newStatus, partnerType ptype){
        String query;
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        if(ptype==partnerType.quarter){
            query = "UPDATE quarterPackage SET status=? WHERE requestID=?";
        }
        else{
            query = "UPDATE partnerPackage SET status=? WHERE requestID=?";
        }
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try {
            boolean updated = manager.commit(stmt, new Object[]{newStatus, key});
            if(!updated){
                ScreenRedirect.launchErrorMsg("Αδυναμία αποδοχής");
            }
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ: "+e.getMessage());
        }
    }

    public static void afterPackageSearchPerform(TourAgency organizerMember, DataSourceManager manager, StringWrapper content){
        List<Map<String, Object>> packages = ScreenConnector.takePackages(organizerMember, manager, content);
        ScreenRedirect.launchPackageListScreen(packages, organizerMember, new PopupWindow<>() {
            @Override
            public void createPopup(Object element, Node anchor, long keySearch) {

                Button detailsBtn = new Button("Λεπτομέρειες");
                Button cancelBtn = new Button("Ακύρωση");
                detailsBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                cancelBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                cancelBtn.setOnAction(e -> {
                    ScreenConnector.changePackageStatus(keySearch, manager, "Ακυρωμένο");
                });
                Package packageElement = (Package) element;

                detailsBtn.setOnAction(e -> {

                        Button optionBtn = new Button("Οριστικοποίηση");
                        optionBtn.setOnAction((event) -> {
                            ScreenConnector.changePackageStatus(keySearch, manager, "Ενεργοποιημένο");
                        });

                        PackageDetailsScreen pds = new PackageDetailsScreen(packageElement, optionBtn, manager);
                });

                Button[] actionButtons;
                voyageStatus status =voyageStatus.fromString(packageElement.getStatus());
                Button cooperationButton = new Button("Αναζήτηση Συνεργασιών");
                if (status == voyageStatus.saved)
                    actionButtons = new Button[]{detailsBtn, cancelBtn, cooperationButton};
                else
                    actionButtons = new Button[]{detailsBtn, cancelBtn};
                cooperationButton.setOnAction(event -> {
                    if (status == voyageStatus.saved)
                        ScreenRedirect.launchPartnerSearchScreen(content, packageElement, manager);
                });

                cooperationButton.setStyle("-fx-background-color: blue; -fx-text-fill: white");
                ScreenRedirect.createPopup(element, anchor, actionButtons);
            }
        });
    }
    public static List<ExtPartner> visualisePartners(List<Map<String, Object>> packageQueryResult, Package pkgRefered){
        List<ExtPartner> selectedPartners = new ArrayList<>();
        for(Map<String, Object> row : packageQueryResult){
            Long partner_id = (Long) row.get("PartnerID");
            String partnerName = String.valueOf(row.get("name"));
            String addressName = String.valueOf(row.get("address"));
            String location = String.valueOf(row.get("location"));
            String schedule = String.valueOf(row.get("schedule"));
            String phone = String.valueOf(row.get("phone"));
            String email = String.valueOf(row.get("email"));
            Long qID = (row.get("quarterID")==null)? null : (Long) row.get("quarterID");
            String description = String.valueOf(row.get("description"));
            partnerType ptype = partnerType.fromString(String.valueOf(row.get("partnerType")));
            ExtPartner partner = new ExtPartner(partner_id, partnerName, addressName, location, schedule, phone, email, description, ptype);
            partner.quarterID=qID;
            selectedPartners.add(partner);
        }
        return selectedPartners;
    }

    public static List<Map<String, Object>> takePartners(Package pkg, DataSourceManager manager, StringWrapper cntnt){
        String query = "SELECT ExtPartner.PartnerID, name, address, location, schedule, phone, email, description, partnerType, quarterID FROM ExtPartner LEFT JOIN LivingQuarter ON ExtPartner.PartnerID = LivingQuarter.partnerID WHERE location=? AND (description LIKE ? OR name LIKE ?)";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        try{
            String desc="%"+cntnt.content+"%";
            var ret = manager.fetch(stmt, new Object[]{pkg.location, desc, desc});
            return ret;
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        return null;
    }

    public static  List<Map<String, Object>> takeRequests(TourAgency agency, DataSourceManager manager){
        String query = "SELECT cooperator_name AS cooperatorName, package_coop AS packageID, cooperation_status AS status, cooperationID AS requestID, AgencyID AS agencyID, source_type AS ptype FROM PartnerCooperationView WHERE agencyID=?";
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
            var ret = manager.fetch(stmt, new Object[]{agency.key});
            return ret;
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        return null;
    }

    public static List<Request> visualiseRequests(List<Map<String, Object>> requestQueryResult){
        List<Request> selectedRequests = new ArrayList<>();
        for(Map<String, Object> row : requestQueryResult){
            String cooperatorName = String.valueOf(row.get("cooperatorName"));
            Long packageID = (Long) row.get("packageID");
            String status = String.valueOf(row.get("status"));
            Long reqID = (Long) row.get("requestID");
            Long agencyID = (Long) row.get("agencyID");
            String type = String.valueOf(row.get("ptype"));
            Request nreq = new Request(cooperatorName, packageID, RequestStatus.fromString(status), reqID, agencyID, partnerType.fromString(type));
            selectedRequests.add(nreq);
        }
        return selectedRequests;
    }


    public static void afterPartnerSearchPerform(Package pkgMember, DataSourceManager manager, StringWrapper content) {
        List<Map<String, Object>> partners = ScreenConnector.takePartners(pkgMember, manager, content);
        ScreenRedirect.launchPartnerListScreen(partners, pkgMember, new PopupWindow<>() {
            @Override
            public void createPopup(Object element, Node anchor, long keySearch) {

                Button detailsBtn = new Button("Λεπτομέρειες");
                detailsBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");

                detailsBtn.setOnAction(e -> {
                    if (element instanceof ExtPartner) {
                        ExtPartner partnerElement = (ExtPartner) element;
                        Button optionButton = new Button("Αποστολή Αιτήματος");
                        if(partnerElement.ptype==partnerType.quarter){
                            Connection db_con = manager.getDb_con();
                            try {
                                if (db_con.isClosed()) {
                                    manager.connect();
                                }
                                PreparedStatement stmt = manager.getDb_con().prepareStatement("SELECT quarterID FROM LivingQuarter WHERE partnerID=?");
                                var res = manager.fetch(stmt, new Object[]{partnerElement.partner_id});
                                var row = res.getFirst();
                                partnerElement.partner_id=(long) row.get("quarterID");
                            }catch (SQLException sqe){
                                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
                            }
                        }
                        optionButton.setOnAction((event)->{ScreenConnector.sendMessage(pkgMember, partnerElement, manager);});
                        ScreenRedirect.launchPartnerDetailsScreen(partnerElement, optionButton);

                    }
                });
                ScreenRedirect.createPopup(element, anchor, new Button[]{detailsBtn});
            }
        });
    }

    public static void search_coop_msg(String location, DataSourceManager manager){

        String query="Select PackageID,email,TourAgency.name,startDate,endDate,description,maxParticipants,price from TourAgency inner join Package on TourAgency.AgencyID=Package.AgencyID where Package.location=? and status='Σε εξέλιξη';";
        PreparedStatement stmt = null;
        String title="Ενεργες εκδρομες στην περιοχη σας";
        Connection db_con = manager.getDb_con();



        try{
            if(db_con.isClosed()){
                manager.connect();
                db_con=manager.getDb_con();
            }
            stmt=db_con.prepareStatement(query);

            List<Map<String,Object>> res =manager.fetch(stmt,new String[]{location});

            PackageListScreen packageListScreen=new PackageListScreen(res,manager,title);


        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ: "+e);
        }



    }

    public static void keywords_transfer(String keywords,DataSourceManager manager) throws IOException {
        String keys[]=keywords.split(",");
        SearchContent searchContent=new SearchContent(keys,manager);

    }

    public static void activate(String nameExtPart, String nameAgency, String emailTourAgent,long packid,DataSourceManager manager) {
        String query="select PartnerID,partnerType from ExtPartner where name=?;";
        String status="Σε αναμονή";
        PreparedStatement stmt = null;


        Connection db_con = manager.getDb_con();

        try{
            if(db_con.isClosed()){
                manager.connect();
                db_con=manager.getDb_con();
            }
            stmt=db_con.prepareStatement(query);


            List<Map<String,Object>> res =manager.fetch(stmt,new String[]{nameExtPart});
            if(res.isEmpty()){
                ScreenRedirect.launchErrorMsg("Συμπληρώστε το όνομα όπως το δηλώσατε στην εγγραφή σας (προσοχή πληκτρολογήστε full name και όχι username)");
                return;
            }
            Map<String, Object> row = res.get(0);
            long KeyPartner=(long)row.get("PartnerID");
            String  type=String.valueOf(row.get("partnerType"));

            Participation participation=new Participation(KeyPartner,status,packid,type);




        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
    }

    public static long IsValidUser(String password, String username, DataSourceManager manager) {
        String query="select password_hash,UserID from User where username=?;";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        long key;

        try{
            if(db_con.isClosed()){
                manager.connect();
                db_con=manager.getDb_con();
            }
            stmt=db_con.prepareStatement(query);

            List<Map<String,Object>> res =manager.fetch(stmt,new String[]{username});
            if(res.isEmpty()){
                ScreenRedirect.launchErrorMsg("Λάθος Όνομα Χρήστη");
                return -1;
            }
            Map<String, Object> row = res.get(0);
            String pass=String.valueOf(row.get("password_hash"));
            key= (long) row.get("UserID");

            if (BCrypt.checkpw(password, pass)) {
                return key;

            } else {
                ScreenRedirect.launchErrorMsg("Λαθος Κωδικος");
                key=-1;
            }

        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");

            key=-1;
        }
        return key;
    }

    public static String GetUserType(String username, DataSourceManager manager) {
        String query="Select type from User where username=?;";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        String type;
        try{
            if(db_con.isClosed()){
                manager.connect();
                db_con=manager.getDb_con();
            }
            stmt=db_con.prepareStatement(query);
            List<Map<String,Object>> res =manager.fetch(stmt,new String[]{username});
            Map<String, Object> row = res.get(0);
            type=String.valueOf(row.get("type"));

        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            type=null;
            
        }
        return type;
    }

    public static long GetPartID(DataSourceManager manager, String usertype, long userID, AtomicLong partPartID) {
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        long partid=-1;
        try {
            manager.connect();
            if(db_con.isClosed()){
                manager.connect();
                db_con=manager.getDb_con();
            }

            if ("client".equals(usertype)) {
                String query="Select customerID from Customer where UserID=? ";
                stmt=db_con.prepareStatement(query);
                List<Map<String,Object>> res =manager.fetch(stmt,new Long[]{userID});
                Map<String, Object> row = res.get(0);
                partid=(long) row.get("customerID");

            }
            else if ("tour_office".equals(usertype)) {
                String query="Select AgencyID from TourAgency where UserID=? ";
                stmt=db_con.prepareStatement(query);
                List<Map<String,Object>> res =manager.fetch(stmt,new Long[]{userID});
                Map<String, Object> row = res.get(0);
                partid=(long) row.get("AgencyID");

            }
            else if ("partner".equals(usertype)) {
                String query="Select PartnerID, partnerType from ExtPartner where UserID=? ";
                stmt=db_con.prepareStatement(query);
                List<Map<String,Object>> res =manager.fetch(stmt,new Long[]{userID});
                Map<String, Object> row = res.get(0);
                partid=(long) row.get("PartnerID");
                partnerType ptype = partnerType.fromString(String.valueOf(row.get("partnerType")));
                if(ptype==partnerType.quarter){
                    query = "Select quarterID from LivingQuarter where partnerID=?";
                    if(db_con.isClosed()){
                        manager.connect();
                        db_con=manager.getDb_con();
                    }
                    stmt=db_con.prepareStatement(query);
                    List<Map<String,Object>> result =manager.fetch(stmt,new Long[]{partid});
                    var nrow = result.getFirst();
                    partPartID.set((long) nrow.get("quarterID"));
                }

            }
            else {
                ScreenRedirect.launchErrorMsg("Not valid");
            }
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στη ΒΔ ");

        }
        return partid;

    }

    public static void sendMessage(Package selPackage, ExtPartner partner, DataSourceManager manager){
        String invitationQueryQuarter = "INSERT INTO quarterPackage (packageID, quarterID, status) VALUES (?, ?, 'Σε Αναμονή');";
        String invitationQueryPartner = "INSERT INTO partnerPackage (packageID, partnerID, status) VALUES (?, ?, 'Σε αναμονή');";

        Connection db_con = manager.getDb_con();
        try{
            if(db_con.isClosed()){
                manager.connect();
                db_con=manager.getDb_con();
            }
            PreparedStatement stmt1 = db_con.prepareStatement(invitationQueryPartner);
            PreparedStatement stmt2 = db_con.prepareStatement(invitationQueryQuarter);
            boolean result;
            if(partner.ptype==partnerType.other){
                 result=manager.commit(stmt1, new Object[]{selPackage.getPackageID(), partner.getPartnerID()});
                 if(!result){
                     ScreenRedirect.launchErrorMsg("Αποτυχία αποστολής αιτήματος");

                 }
            }
            else{
                result=manager.commit(stmt2, new Object[]{selPackage.getPackageID(), partner.getQuarterID()});
                if(!result){
                    ScreenRedirect.launchErrorMsg("Αποτυχία αποστολής αιτήματος");
                }
            }
        }catch (SQLException sqle){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
    }
    public static void afterCommitPerform(Customer client, DataSourceManager manager, StringWrapper content) {
        ScreenRedirect.launchFilterScreen(client, manager, content);
    }


    public static void getBucketDetails(DataSourceManager manager, long customerId) {
        String query = "SELECT PackageId,CustomerId,people,description,price,location  from ReservationBucket where CustomerId=? ;";

        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
            var ret = manager.fetch(stmt, new Object[]{customerId});
            ScreenRedirect.launchPackageListScreen(manager,ret,null,"Εμφανηση Πακετων καλαθιου");

        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }

    }

    public static List<Map<String, Object>> ShowReq(DataSourceManager manager,ExtPartner partner) {
        String query;
        Long pusID;
        if(partner.ptype==partnerType.quarter){
            query = "SELECT quarterPackage.requestID, ExtPartner.name, quarterPackage.packageID, quarterPackage.status FROM quarterPackage JOIN LivingQuarter ON quarterPackage.quarterID = LivingQuarter.quarterID JOIN ExtPartner  on ExtPartner.PartnerID = LivingQuarter.partnerID WHERE quarterPackage.quarterID=?";
            pusID = partner.getQuarterID();
        }
        else{
            query = "SELECT partnerPackage.requestID, ExtPartner.name, partnerPackage.packageID, partnerPackage.status FROM partnerPackage JOIN ExtPartner  on ExtPartner.PartnerID = partnerPackage.partnerID WHERE partnerPackage.partnerID=?";
            pusID=partner.getPartnerID();
        }
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
            var ret = manager.fetch(stmt, new Object[]{pusID});

            for (Map<String, Object> row : ret) {
                Long id=(Long) row.get("requestID");
                String cooperatorName = String.valueOf(row.get("name"));
                Long packageID = row.get("packageID") != null ? (Long) row.get("packageID") : null;
                String status = String.valueOf(row.get("status"));
            }

            return ret;


        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
        return null;
    }

    public static List<Request> sendReq(List<Map<String, Object>> requestQueryResult){
        List<Request> selectedRequests = new ArrayList<>();
        for(Map<String, Object> row : requestQueryResult){

            Long reqID=(Long) row.get("requestID");
            String cooperatorName = String.valueOf(row.get("name"));
            Long packageID = (Long) row.get("packageID");
            String status = String.valueOf(row.get("status"));


            Request nreq = new Request(cooperatorName, packageID, RequestStatus.fromString(status),reqID);
            selectedRequests.add(nreq);
        }
        return selectedRequests;
    }

    public static void check_coop_status(Request request, DataSourceManager manager) {
        String status = request.getStatus();
        if (status.equals("Ακυρωμένη")) {
            ScreenRedirect.launchErrorMsg("Δεν μπορειτε να τροποποιησετε μια ηδη ακυρωμενη αιτηση");
        } else {
            ExtPartner ext = getExt();
            Long packageID = (Long) request.getPackageID();

            String q = "Select PackageID,email,price,TourAgency.name,startDate,endDate,description,maxParticipants from TourAgency inner join Package on TourAgency.AgencyID=Package.AgencyID where Package.PackageID=?;";
            PreparedStatement stmt = null;
            Connection db_con = manager.getDb_con();
            manager.connect();

            try {

                if (db_con.isClosed())
                    manager.connect();
                stmt = manager.getDb_con().prepareStatement(q);

                var ret = manager.fetch(stmt, new Object[]{packageID});
                List<Package> separated = ScreenRedirect.send(ret);
                PackageDetailsScreen detailsScreen=new PackageDetailsScreen(separated,manager,"Λεπτομέρειες Πακέτου για τροποιηση");


            } catch (Exception e) {
                ScreenRedirect.launchErrorMsg("Δεν βρηκα το πακετο: "+e);
            }
        }
    }

    public static void approveRequest(Request req, DataSourceManager manager){
        String requestName = req.getName();
        String query_ptype = "SELECT partnerType FROM ExtPartner WHERE name = ?";

        Connection db_con = manager.getDb_con();
        try{
            if(db_con.isClosed()){
                manager.connect();
                db_con=manager.getDb_con();
            }
            PreparedStatement stmt = db_con.prepareStatement(query_ptype);
            List<Map<String, Object>> queryResult = manager.fetch(stmt, new Object[]{requestName});
            if(!queryResult.isEmpty()){
                Map<String, Object> row = queryResult.getFirst();
                partnerType ptype = partnerType.fromString(String.valueOf(row.get("partnerType")));
                String query2;
                if(ptype==partnerType.quarter){
                    query2 = "UPDATE quarterPackage SET status='Σε ισχύ' WHERE requestID=?";
                }
                else
                    query2 = "UPDATE partnerPackage SET status='Σε ισχύ' WHERE requestID=?";
                if(db_con.isClosed()){
                    manager.connect();
                    db_con=manager.getDb_con();
                }
                PreparedStatement stmt2 = db_con.prepareStatement(query2);
                boolean result = manager.commit(stmt2, new Object[]{req.getRequestID()});
                if(!result){
                    ScreenRedirect.launchErrorMsg("Αδυναμία αποδοχής");
                }
            }
        }catch (SQLException sqe){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }
    }

    public static boolean check_bounds(double v) {
        Random random = new Random();
        boolean value = random.nextBoolean();
        return value;
    }

    public static boolean check_respect(String text) {
        Random random = new Random();
        boolean valid = random.nextBoolean();
        return valid;
    }
}



