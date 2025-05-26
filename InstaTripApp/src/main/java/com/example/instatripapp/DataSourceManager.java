package com.example.instatripapp;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSourceManager {
    private Connection db_con=null;
    public boolean success;
    public String errMesg;
    public void connect(){
        db_con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host="mysql-db-softeng-sotengproject-5ac4.j.aivencloud.com";
            String port="16452";
            String databaseName="defaultdb";
            String userName="avnadmin";
            String password = "AVNS_I_yry1Vd-4HSsbipDDw";
            db_con=DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);
            success=true;
        }catch (Exception exp){
            success=false;
            db_con=null;
        }

    }

    private void end(){
        if(db_con!=null && success){
            try {
                db_con.close();
                success=true;
            }catch (SQLException exp){
                db_con=null;
                success=false;
            }
        }
    }

    //in this function only a select query is sent for searching no modification query is suitable here

    public List<Map<String, Object>> fetch(PreparedStatement stmt, Object[] args) throws SQLException {
        if(db_con.isClosed()){
            connect();
        }
        List<Map<String, Object>> results=new ArrayList<>();
        if(args!=null) {
            for (int j = 0; j < args.length; ++j) {
                stmt.setObject(j + 1, args[j]);
            }
        }

        try{
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            while(rs.next()){
                Map<String, Object> row = new HashMap<>();
                for(int j=1; j<=columnCount; ++j){
                    row.put(rsMetaData.getColumnLabel(j), rs.getObject(j));
                }
                results.add(row);
            }
        }catch (SQLException excp){
            errMesg = new String(excp.getMessage());
            end();
            return null;
        }
        end();
        return results;
    }

    //this is for modification queries

    public boolean commit(PreparedStatement stmt, Object[] args) throws SQLException{
        if(db_con.isClosed()){
            connect();
        }
        if(args!=null) {
            for (int j = 0; j < args.length; ++j) {
                stmt.setObject(j + 1, args[j]);
            }
        }
        try {
            stmt.executeUpdate();
            int udateCount = stmt.getUpdateCount();
            if(udateCount>0) {
                end();
                return true;
            }
        }catch (SQLException excp){
            errMesg = new String(String.valueOf(excp));
            end();
            return false;
        }
        end();
        return false;
    }

    public Connection getDb_con(){
        return db_con;
    }
}


