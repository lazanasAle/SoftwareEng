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

import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
                // handle missing date - maybe set default or throw
                System.out.println("startDate is missing");
            }

            Date end = (Date) row.get("endDate");
            LocalDate endDate = null;
            if (end != null) {
                endDate = end.toLocalDate();
            } else {
                System.out.println("endDate is missing");
            }

            String description=String.valueOf(row.get("description"));

            Long maxParticipantsObj = (Long) row.get("maxParticipants");
            long maxParticipants = (maxParticipantsObj != null) ? maxParticipantsObj : 0;

            long PackID;
            double Price;
            String Location;
            Long custId;
            int people;
            voyageStatus status=voyageStatus.fromString(String.valueOf(row.get("status")));

            if((Long)row.get("CustomerId")!=null){
                custId=(Long)row.get("CustomerId");
            }
            else {custId=null;}

            if(row.get("people")!=null)  people=(int)row.get("people");
            else people=-1;

            Object pkgId = row.get("PackageID");
            if (pkgId == null) pkgId = row.get("PackageId");
            if (pkgId == null) pkgId = row.get("ReservationBucket.PackageId");

            PackID = (pkgId != null) ? (Long) pkgId : -1L;

            System.out.println("Row keys: " + row.keySet());

            if(row.get("price")!=null){
                Price=Double.parseDouble(row.get("price").toString());
            }
            else{Price=-1;}

            Location=String.valueOf(row.get("location"));




            System.out.println("PackageID: " + PackID);
            System.out.println("Email: " + email);
            System.out.println("Name: " + name);
            System.out.println("Start Date: " + startDate);
            System.out.println("End Date: " + endDate);
            System.out.println("Description: " + description);
            System.out.println("Max Participants: " + maxParticipants);

            System.out.println("Price: " + Price);
            System.out.println("Location: " + Location);
            System.out.println("Status: " + status);
            System.out.println("CustomerId: " + custId);
            System.out.println("people: " + people);
            System.out.println("----------------------------------------");


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
    public static void launchFilterScreen(Customer client, DataSourceManager manager, StringWrapper content) {
        FilterScreen filter=new FilterScreen(content,manager,client);
    }

    public static void launchReservationForm(Package pkg, DataSourceManager manager) {
        ReservationFormScreen reservationFormScreen=new ReservationFormScreen(pkg,manager);
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
                    ScreenConnector.changeStatus(keySearch, manager, "Ακυρωμένο");
                });
                Package packageElement = (Package) element;

                detailsBtn.setOnAction(e -> {

                        Button optionBtn = new Button("Οριστικοποίηση");
                        optionBtn.setOnAction((event) -> {
                            ScreenConnector.changeStatus(keySearch, manager, "Ενεργοποιημένο");
                        });

                        PackageDetailsScreen pds = new PackageDetailsScreen(packageElement, optionBtn);
                });
                Button cooperationButton = new Button("Αναζήτηση Συνεργασιών");
                cooperationButton.setOnAction(event -> {
                    voyageStatus status =voyageStatus.fromString(packageElement.getStatus());
                    if (status == voyageStatus.saved)
                        ScreenRedirect.launchPartnerSearchScreen(content, packageElement, manager);
                });

                cooperationButton.setStyle("-fx-background-color: blue; -fx-text-fill: white");
                ScreenRedirect.createPopup(element, anchor, new Button[]{detailsBtn, cancelBtn, cooperationButton});
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
            String description = String.valueOf(row.get("description"));
            partnerType ptype = partnerType.fromString(String.valueOf(row.get("partnerType")));
            ExtPartner partner = new ExtPartner(partner_id, partnerName, addressName, location, schedule, phone, email, description, ptype);
            selectedPartners.add(partner);
        }
        return selectedPartners;
    }

    public static List<Map<String, Object>> takePartners(Package pkg, DataSourceManager manager, StringWrapper cntnt){
        String query = "SELECT PartnerID, name, address, location, schedule, phone, email, description, partnerType FROM ExtPartner WHERE location=? AND (description LIKE ? OR name LIKE ?);";
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
                        optionButton.setOnAction((event)->{ScreenConnector.sendMessage(pkgMember, partnerElement, manager);});
                        ScreenRedirect.launchPartnerDetailsScreen(partnerElement, optionButton);

                    }
                });
                ScreenRedirect.createPopup(element, anchor, new Button[]{detailsBtn});
            }
        });
    }

    public static void search_coop_msg(String location, DataSourceManager manager){

        String query="Select PackageID,email,TourAgency.name,startDate,endDate,description,maxParticipants from TourAgency inner join Package on TourAgency.AgencyID=Package.AgencyID where Package.location=? and status='Σε εξέλιξη';";
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
            //ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            System.out.println(e);
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
            Map<String, Object> row = res.get(0);
            long KeyPartner=(long)row.get("PartnerID");
            String  type=String.valueOf(row.get("partnerType"));
            System.out.println(KeyPartner + packid);

            Participation participation=new Participation(KeyPartner,status,packid,type);




        }catch (SQLException e){
            //ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            System.out.println(e);
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
                System.out.println("✅ Login successful");
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
            System.out.println(type);

        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            System.out.println(e);
            type=null;
            
        }
        return type;
    }

    public static long GetPartID(DataSourceManager manager, String usertype, long userID) {
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
                String query="Select PartnerID from ExtPartner where UserID=? ";
                stmt=db_con.prepareStatement(query);
                List<Map<String,Object>> res =manager.fetch(stmt,new Long[]{userID});
                Map<String, Object> row = res.get(0);
                partid=(long) row.get("PartnerID");

            }
            else {
                ScreenRedirect.launchErrorMsg("Not valid");
            }
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφαλμα στη ΒΔ "+e);

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
                result=manager.commit(stmt2, new Object[]{selPackage.getPackageID(), partner.getPartnerID()});
                if(!result){
                    ScreenRedirect.launchErrorMsg("Αποτυχία αποστολής αιτήματος");
                }
            }
        }catch (SQLException sqle){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ "+sqle);
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
}



