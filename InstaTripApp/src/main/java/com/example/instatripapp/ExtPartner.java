package com.example.instatripapp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

enum partnerType {
    quarter,
    other;

    public static String toString(partnerType tp){
        return (tp==quarter)? "Χώρος Διαμονής" : "Άλλος συνεργάτης";
    }

    public static partnerType fromString(String str){
        return (str.equals("Χώρος Διαμονής"))?  quarter : other;
    }
}

public class ExtPartner implements Searchable {
    Long partner_id;
    String partnerName;
    String addressName;
    String location;
    String schedule;
    String phone;
    String email;
    String description;
    partnerType ptype;


    public ExtPartner(Long partner_id, String partnerName, String addressName, String location, String schedule, String phone, String email, String description, partnerType ptype){
        this.partner_id=partner_id;
        this.partnerName=partnerName;
        this.addressName=addressName;
        this.location=location;
        this.schedule=schedule;
        this.phone=phone;
        this.email=email;
        this.description=description;
        this.ptype=ptype;
    }

    public ExtPartner(String username,DataSourceManager manager){
        String query="Select address,location,phone,email from ExtPartner where name=?";
        PreparedStatement stmt = null;

        Connection db_con = manager.getDb_con();
        try{
            stmt=manager.getDb_con().prepareStatement(query);

            List<Map<String,Object>> res =manager.fetch(stmt,new String[]{username});

            var row=res.getFirst();

            addressName=new String(String.valueOf(row.get("address")));
            location=new String(String.valueOf(row.get("location")));
            phone=new String(String.valueOf(row.get("phone")));
            email=new String(String.valueOf(row.get("email")));

            //System.out.println(address + location + phone + email);

        }catch (Exception e){
            System.out.println(e);

        }
    }

    public ExtPartner(long key) {
        this.partner_id=key;

    }

    public void SearchCooparation(DataSourceManager manager){
        //System.out.println(this.location);
        ScreenConnector.search_coop_msg(this.location,manager);

    }

    @Override
    public long getKey() {
        return partner_id;
    }

    public Long getPartnerID(){
        return partner_id;
    }

    public String getName(){
        return partnerName;
    }

    public String getAddress(){
        return addressName;
    }

    public String getPhone(){
        return phone;
    }

    public String getLocation(){
        return location;
    }

    public String getPartnerType(){
        return partnerType.toString(ptype);
    }

    public String getEmail(){
        return email;
    }
}

