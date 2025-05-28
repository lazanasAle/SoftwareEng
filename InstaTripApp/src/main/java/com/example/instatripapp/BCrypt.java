package com.example.instatripapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


//Use of bcrypt for hashed passwords
//initialization for users that are already in the db
class Bcrypt {
    String plainPassword = "Instatrip";
    String hashedPassword;

    //initialization for users that are already in the db
    public Bcrypt(DataSourceManager manager){
        hashedPassword=BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        String query="update User set password_hash=?;";
        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        try {
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);
            manager.commit(stmt,new Object[]{hashedPassword});
        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }

    }

}