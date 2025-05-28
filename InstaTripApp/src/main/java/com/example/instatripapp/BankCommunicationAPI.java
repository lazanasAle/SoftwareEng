package com.example.instatripapp;

import java.util.Random;


public class BankCommunicationAPI {


    public boolean checkCard(CardDescription card){
        Random rand = new Random();
        return rand.nextBoolean();
    }


    public boolean tryCharging(CardDescription card){
        Random rand = new Random();
        return rand.nextBoolean();
    }

    public boolean transfer(CardDescription card, double money){
        Random rand = new Random();
        return rand.nextBoolean();
    }
}

