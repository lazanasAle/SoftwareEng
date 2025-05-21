package com.example.instatripapp;

import java.sql.*;


public class BankCommunicationAPI {
    private Connection db_con=null;
    private double amount;

    public BankCommunicationAPI(){
        try {
            String host = "pg-bank-db-sotengproject-5ac4.e.aivencloud.com";
            String port = "16452";
            String databaseName = "defaultdb";
            String uname = "avnadmin";
            String password = "AVNS_IE0_9XJJ0zva-QhQtVx";
            db_con=DriverManager.getConnection("jdbc:postgresql://"+host+":16452/"+databaseName+"?ssl=require&user="+uname+"&password="+password);
        }catch (Exception exc){
            System.out.println(exc);
        }
    }

    public void end(){
        try {
            if (db_con != null && !db_con.isClosed()) {
                db_con.close();
            }
        }catch(SQLException sqe){
            db_con=null;
        }
    }

    public boolean checkCard(CardDescription card){
            String query = "SELECT * FROM carddescription WHERE card_number=? AND owner_name=? AND cvv=?;";
        try {
            PreparedStatement stmt=db_con.prepareStatement(query);
            stmt.setLong(1, card.getCardNumber());
            stmt.setString(2, card.getOwner_name());
            stmt.setShort(3, card.getCvv());

            ResultSet rsCard=stmt.executeQuery();
            if(rsCard.next()){
                amount=rsCard.getDouble("amount");
                return true;
            }
            else
                return false;
        } catch (SQLException e) {
            ScreenRedirect.launchErrorMsg("Σφάλμα κατά την επικοινωνία με την τράπεζα");
        }
        return false;
    }


    public boolean tryCharging(CardDescription card, double money){
        if (amount<money){
            return false;
        }
        String query = "UPDATE carddescription SET amount=? WHERE card_number=? AND owner_name=? AND cvv=?;";
        try{
            PreparedStatement stmt=db_con.prepareStatement(query);
            stmt.setDouble(1, amount-money);
            stmt.setLong(2, card.getCardNumber());
            stmt.setString(3, card.getOwner_name());
            stmt.setShort(4, card.getCvv());

            int rows=stmt.executeUpdate();
            if(rows<=0){
                return false;
            }
            else
                return true;
        }catch (SQLException exe){
            ScreenRedirect.launchErrorMsg("Σφάλμα κατά την επικοινωνία με την τράπεζα");
            return false;
        }
    }

    public boolean transfer(CardDescription card, double money){
        String query = "UPDATE carddescription SET amount=? WHERE card_number=? AND owner_name=? AND cvv=?;";
        try{
            PreparedStatement stmt=db_con.prepareStatement(query);
            stmt.setDouble(1, amount+money);
            stmt.setLong(2, card.getCardNumber());
            stmt.setString(3, card.getOwner_name());
            stmt.setShort(4, card.getCvv());

            int rows=stmt.executeUpdate();
            if(rows<=0){
                return false;
            }
            else
                return true;
        }catch (SQLException exe){
            ScreenRedirect.launchErrorMsg("Σφάλμα κατά την επικοινωνία με την τράπεζα");
            return false;
        }
    }
}

