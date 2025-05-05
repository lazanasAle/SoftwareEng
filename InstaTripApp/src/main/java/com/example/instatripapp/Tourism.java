package com.example.instatripapp;

import java.util.*;


class TourAgency{
    // To be filled with more attributes and functions
}

class Program{
    // To be filled with more attributes and functions
}


class Voyage{
    public final TourAgency organizer;
    private LivingQuarter shelter;
    public Program program;
    public List<ExtPartner> partners;

    // To be filled with more attributes and functions

    Voyage(TourAgency organizer, LivingQuarter shelter, Program program){
        this.organizer=organizer;
        this.shelter=shelter;
        this.program=program;
        partners=new Vector<>();
    }

    // To be filled with more attributes and functions

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