package com.example.instatripapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;




class CancellationContents {
    Request req;
    String reqstat="Ακυρωμένη";
    DataSourceManager manager;

    public CancellationContents(Request req,DataSourceManager manager){
        this.req=req;
        this.manager=manager;
        ChangeStat(req.getRequestID(),this.manager);
    }

    public void ChangeStat(Long id,DataSourceManager manager){
        String name=req.getName();
        String query = "select partnerType from ExtPartner where name=?";

        PreparedStatement stmt = null;
        Connection db_con = manager.getDb_con();
        manager.connect();

        try {
            
            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);

            var ret = manager.fetch(stmt, new Object[]{name});
            var row=ret.get(0);
            String type=String.valueOf(row.get("partnerType"));
            System.out.println(type);
            if(type.equals("Χώρος Διαμονής")){
                String q="update quarterPackage set status=? where requestID=?";
                if(db_con.isClosed())
                    manager.connect();
                stmt=manager.getDb_con().prepareStatement(q);
                var result=manager.commit(stmt,new Object[]{reqstat,id});
                if(!result){
                    ScreenRedirect.launchErrorMsg("Αποτυχία αλλαγης καταστασης στην quarterPackage");
                }
            }
            else{
                String q="update partnerPackage set status=? where requestID=?";
                if(db_con.isClosed())
                    manager.connect();
                stmt=manager.getDb_con().prepareStatement(q);
                var result=manager.commit(stmt,new Object[]{reqstat,id});
                if(!result){
                    ScreenRedirect.launchErrorMsg("Αποτυχία αλλαγης καταστασης στην partnerPackage");
                }
            }

        }catch (SQLException e){
            System.out.println(e);
            //ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
        }

    }
}
