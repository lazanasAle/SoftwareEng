package com.example.instatripapp;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

public class Package implements Searchable{

    public TourAgency organizer;
    private LivingQuarter shelter;
    public Program program;
    public List<ExtPartner> partners;
    public String location;
    public double price;
    private voyageStatus status;
    public long maxParticipants;
    public LocalDate startDate;
    public LocalDate endDate;
    private long key;
    private String description;
    private String name;
    private String email;
    private long custId;
    private int people;

    Package(TourAgency organizer, LivingQuarter shelter, Program program){
        this.organizer=organizer;
        this.shelter=shelter;
        this.program=program;
        partners=new Vector<>();
    }

    Package(){
        name=new String();
        email=new String();
        description=new String();
    }

    public void initializePackage(String location, double price, long maxParticipants, voyageStatus status, LocalDate startDate, LocalDate endDate){
        this.location = new String(location);
        this.maxParticipants=maxParticipants;
        this.price=price;
        this.status=status;
        this.startDate=startDate;
        this.endDate=endDate;
    }



    public void initializePackage(String location, double price, long maxParticipants, voyageStatus status, LocalDate startDate, LocalDate endDate, long key, String description){
        initializePackage(location, price, maxParticipants, status, startDate, endDate);
        this.key=key;
        this.description=description;
    }

    public void initializePackage(String location, double price, long maxParticipants, voyageStatus status, LocalDate startDate, LocalDate endDate, String description){
        initializePackage(location, price, maxParticipants, status, startDate, endDate);
        this.description=description;
    }

    //Αυτη χρησιμοποιω
    public void initializePackage(long key, LocalDate endDate, String name, String description, String email, LocalDate startDate, long maxParticipants, String location, double price, voyageStatus status, long custId, int people){
        this.name=name;
        this.email=email;
        this.description=description;
        this.startDate=startDate;
        this.endDate=endDate;
        this.maxParticipants=maxParticipants;
        this.key=key;
        this.location=location;
        this.price=price;
        this.status=status;
        this.custId=custId;
        this.people=people;
    }

    public long getAgencyID(){
        return organizer.key;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }
    public double getPrice(){
        return price;
    }
    public String getLocation(){
        return location;
    }
    public long getPackageID(){
        return key;
    }
    public long getMaxParticipants(){
        return maxParticipants;
    }
    public String getStatus(){
        return voyageStatus.toString(this.status);
    }
    public String getDescription(){
        return this.description;
    }
    public String getEmail(){
        return email;
    }
    public String getName() {
        return name;
    }
    public long getCustomerId() {
        return custId;
    }
    public long getPackageId(){return key;}
    public int getPeople(){return people;}
    public void setPeople(int people){
        this.people=people;
    }

    @Override
    public long getKey(){
        return key;
    }

}