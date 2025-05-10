package com.example.instatripapp;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ScreenRedirect {
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
}
