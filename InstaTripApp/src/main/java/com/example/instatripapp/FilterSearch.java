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
    Customer customer;


    public FilterSearch(String place, StringWrapper content, DataSourceManager manager, Customer customer){
        this.content=content;
        this.place=place;
        this.manager=manager;
        this.customer=customer;
        queryResult=GetSearchWithPlace(content,place,manager);
        ScreenRedirect.launchPackageListScreen(manager,queryResult,customer);

    }
    public FilterSearch(double price, StringWrapper content, DataSourceManager manager, Customer customer){
        this.content=content;
        this.price=price;
        this.manager=manager;
        this.customer=customer;
        queryResult=GetSearchWithPrice(content,price,manager);
        ScreenRedirect.launchPackageListScreen(manager,queryResult,customer);
    }

    public List<Map<String, Object>> GetSearchWithPlace(StringWrapper content,String place,DataSourceManager manager){
        String query="select PackageID,name,email,startDate,endDate,price,Package.location,description,status,Package.maxParticipants from Package inner join TourAgency on Package.AgencyID = TourAgency.AgencyID where description like ? and Package.location=? and status='Σε εξέλιξη';";

        PreparedStatement stmt = null;

        Connection db_con = manager.getDb_con();


        try{

                if(db_con.isClosed())
                    manager.connect();
                stmt=manager.getDb_con().prepareStatement(query);


                String desc="%"+content.content+"%";
                var ret = manager.fetch(stmt, new Object[]{desc,place});
                return ret;




        }catch (SQLException e){
            ScreenRedirect.launchErrorMsg("Σφάλμα στην ΒΔ");

        }
        return null;
    }

    public List<Map<String, Object>> GetSearchWithPrice(StringWrapper content,double price,DataSourceManager manager){
        String query="select PackageID,name,email,startDate,endDate,price,Package.location,description,status,Package.maxParticipants from Package inner join TourAgency on Package.AgencyID = TourAgency.AgencyID  where description like ? and price<=? and status='Σε εξέλιξη';";

        PreparedStatement stmt = null;

        Connection db_con = manager.getDb_con();


        try{

            if(db_con.isClosed())
                manager.connect();
            stmt=manager.getDb_con().prepareStatement(query);

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
