package com.example.instatripapp;

public class Customer {
    private long customerId;

    public Customer(long customerId){
        this.customerId=customerId;
    }

    public void searchpack(DataSourceManager manager){
        StringWrapper cntnt = new StringWrapper("");
        ScreenRedirect.launchSearchScreen(cntnt,manager,this);
    }

    public void prepareReservation(DataSourceManager manager) {
        ScreenConnector.getBucketDetails(manager,this.customerId);
    }

}






class Reservation {
    public final Customer reserver;
    public Room reservedRoom;
    public Package voyageRefered;


    // To be filled with more attributes and functions

    Reservation(Customer reserver){
        this.reserver=reserver;
    }

    // To be filled with more attributes and functions
}
