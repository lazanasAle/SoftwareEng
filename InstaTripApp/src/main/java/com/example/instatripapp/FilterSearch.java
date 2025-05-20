package com.example.instatripapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

class FilterSearch {
    StringWrapper content;
    String place;
    double price;
    List<Map<String, Object>> queryResult;
    DataSourceManager manager;


    public FilterSearch(String place,StringWrapper content,DataSourceManager manager){
        this.content=content;
        this.place=place;
        this.manager=manager;
        queryResult=GetSearchWithPlace(content,place,manager);
        ScreenRedirect.launchPackageListScreen(manager,queryResult);

    }
    public FilterSearch(double price,StringWrapper content,DataSourceManager manager){
        this.content=content;
        this.price=price;
        this.manager=manager;
        queryResult=GetSearchWithPrice(content,price,manager);
        ScreenRedirect.launchPackageListScreen(manager,queryResult);
    }

    public List<Map<String, Object>> GetSearchWithPlace(StringWrapper content,String place,DataSourceManager manager){
        String query="select PackageID,startDate,endDate,price,description,status,Package.maxParticipants from Package where description like ? and location=? and status='Σε εξέλιξη';";

        PreparedStatement stmt = null;

        Connection db_con = manager.getDb_con();


        try{

                if(db_con.isClosed())
                    manager.connect();
                stmt=manager.getDb_con().prepareStatement(query);

                ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");


                String desc="%"+content.content+"%";
                var ret = manager.fetch(stmt, new Object[]{desc,place});
                return ret;




        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            System.out.println(e);
        }
        return null;
    }

    public List<Map<String, Object>> GetSearchWithPrice(StringWrapper content,double price,DataSourceManager manager){
        String query="select PackageID,startDate,endDate,price,description,status,Package.maxParticipants from Package where description like ? and price<=? and status='Σε εξέλιξη';";

        PreparedStatement stmt = null;

        Connection db_con = manager.getDb_con();


        try{

            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);

            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");


            String desc="%"+content.content+"%";
            var ret = manager.fetch(stmt, new Object[]{desc,price});
            return ret;




        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");
            System.out.println(e);
        }
        return null;
    }
}
