package com.example.instatripapp;

public class CardDescription {
    private long cardNumber;
    private String owner_name;
    private short cvv;

    public CardDescription(long cardNumber, String owner_name, short cvv){
        this.cardNumber=cardNumber;
        this.cvv=cvv;
        this.owner_name=owner_name;
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
}
