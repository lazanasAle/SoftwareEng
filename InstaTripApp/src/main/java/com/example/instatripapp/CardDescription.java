package com.example.instatripapp;

import java.util.Random;

public class CardDescription {
    private long cardNumber;
    private String owner_name;
    private short cvv;
    private BankCommunicationAPI bank;
    boolean con;
    Package pkg;

    public CardDescription(long cardNumber, String owner_name, short cvv, Package pkg){
        this.cardNumber=cardNumber;
        this.cvv=cvv;
        this.pkg=pkg;
        this.owner_name=owner_name;
        bank=new BankCommunicationAPI();
        System.out.println(con);
        confirm_reject();
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getOwner_name(){
        return owner_name;
    }
    public short getCvv(){
        return cvv;
    }

    public void confirm_reject(){
        con=bank.checkCard(this);
        System.out.println(con);
        if(con){
            Random rand = new Random();
            chargeCard(this,rand.nextDouble(500));
        }
        else{
            ScreenRedirect.launchErrorMsg("Δεν ειναι σωστα τα στοιχεια σας.Βαλτε τα Ξανα");
        }
    }
    public void chargeCard(CardDescription card,double money){
        if(bank.tryCharging(card,money)){
            FinalReservation finalReservation=new FinalReservation(pkg);
        }
        else{
            ScreenRedirect.launchErrorMsg("Δεν υπαρχουν αρκετα λεφτα στην καρτα σας");
        }

    }


}
