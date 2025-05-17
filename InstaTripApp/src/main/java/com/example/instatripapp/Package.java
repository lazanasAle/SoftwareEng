package com.example.instatripapp;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

public class Package implements Searchable{

    public final TourAgency organizer;
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


    Package(TourAgency organizer, LivingQuarter shelter, Program program){
        this.organizer=organizer;
        this.shelter=shelter;
        this.program=program;
        partners=new Vector<>();
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

    @Override
    public long getKey(){
        return key;
    }

}