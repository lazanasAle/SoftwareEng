package com.example.instatripapp;


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

