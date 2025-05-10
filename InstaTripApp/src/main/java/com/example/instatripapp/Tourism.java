package com.example.instatripapp;

import java.time.LocalDate;
import java.util.*;


enum voyageStatus{saved, in_progress, canceled};

class TourAgency{

    public int key;

    public TourAgency(int key){
        this.key=key;
    }


    void createPackage(DataSourceManager manager){
        CreatePackageForm newPackageForm = new CreatePackageForm(this, manager);
    }
}

class Program{
    // To be filled with more attributes and functions
}


class Package{

    public final TourAgency organizer;
    private LivingQuarter shelter;
    public Program program;
    public List<ExtPartner> partners;
    public String location;
    public double price;
    private voyageStatus status;
    public int maxParticipants;
    public LocalDate startDate;
    public LocalDate endDate;

    Package(TourAgency organizer, LivingQuarter shelter, Program program){
        this.organizer=organizer;
        this.shelter=shelter;
        this.program=program;
        partners=new Vector<>();
    }

    public void initializePackage(String location, double price, int maxParticipants, voyageStatus status, LocalDate startDate, LocalDate endDate){
        this.location = new String(location);
        this.maxParticipants=maxParticipants;
        this.price=price;
        this.status=status;
        this.startDate=startDate;
        this.endDate=endDate;
    }

}


class Participation {
    public final TourAgency organizer;
    public ExtPartner partner;
    public LivingQuarter shelter;

    // To be filled with more attributes and functions

    Participation(TourAgency agency, ExtPartner partner, LivingQuarter shelter){
        organizer=agency;
        this.partner=partner;
        this.shelter=shelter;
    }

    // To be filled with more attributes and functions

}