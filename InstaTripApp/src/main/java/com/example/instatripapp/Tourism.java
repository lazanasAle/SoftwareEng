package com.example.instatripapp;

import java.util.*;


class TourAgency{
    // To be filled with more attributes and functions
}

class Program{
    // To be filled with more attributes and functions
}

class Feedback {
    public final Customer writer;

    // To be filled with more attributes and functions

    Feedback(Customer writer){
        this.writer=writer;
    }

    // To be filled with more attributes and functions
}

class FeedbackAnswer{
    public final Feedback reference;
    public final TourAgency done_by;

    // To be filled with more attributes and functions

    FeedbackAnswer(Feedback reference, TourAgency done_by){
        this.reference=reference;
        this.done_by=done_by;
    }

    // To be filled with more attributes and functions

}

class FeedbackPage{
    private HashSet<Feedback> critics;
    private HashSet<FeedbackAnswer> answers;

    // To be filled with more attributes and functions

    FeedbackPage(){
        critics=new HashSet<>();
        answers= new HashSet<>();
    }

    // To be filled with more attributes and functions

}

class Voyage{
    public final TourAgency organizer;
    private LivingQuarter shelter;
    public Program program;
    public List<ExtPartner> partners;
    public FeedbackPage criticsPage;

    // To be filled with more attributes and functions

    Voyage(TourAgency organizer, LivingQuarter shelter, Program program){
        this.organizer=organizer;
        this.shelter=shelter;
        this.program=program;
        partners=new Vector<>();
        criticsPage=new FeedbackPage();
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