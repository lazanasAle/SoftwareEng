package com.example.instatripapp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static com.example.instatripapp.MainScreen.manager;

class TemporaryReservation {
    Package pkg;
    int people;
    String status="Σε αναμονή πληρωμής";
    long custId;
    long packid;
    long roomid; //random ολα τα δωματια θα εχουν 2 ή 3 ατομα

    TemporaryReservation(Package pkg){
        this.pkg=pkg;
        people= pkg.getPeople();
        custId=pkg.getCustomerId();
        packid=pkg.getPackageId();
        Random rand = new Random();
        roomid=rand.nextLong(6);
        System.out.println(roomid);
        SaveTo();
    }
    public void SaveTo(){
        String query = "INSERT INTO Reservation (people,status,CustomerID,PackageID,RoomID) VALUES (?,?,?,?,?);";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();

        try {
            if(db_con.isClosed()){
                manager.connect();
            }
            stmt = manager.getDb_con().prepareStatement(query);
            boolean inserted=manager.commit(stmt, new Object[]{people,status,custId,packid,roomid});
            if(!inserted){
                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            }
        } catch (SQLException e) {

            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }

    }

}
