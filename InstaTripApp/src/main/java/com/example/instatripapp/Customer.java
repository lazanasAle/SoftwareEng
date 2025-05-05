package com.example.instatripapp;


enum reservation_status{finished, canceled, waiting_payment}


public class Customer {
    private int key;
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String location;

    public Customer(int key, String firstName, String lastName, String email, String location){
        this.key=key;
        this.firstName=new String(firstName);
        this.lastName=new String(lastName);
        this.email=new String(email);
        this.location=new String(location);
    }

}
class Reservation {
    private int key;
    public int people;
    public reservation_status status;
    public final Customer reserver;
    public Room reservedRoom;
    public Voyage voyageReferred;

    Reservation(int key, int people, Customer reserver, Voyage voyageReferred, Room room){
        this.key=key;
        this.people=people;
        this.reserver=reserver;
        this.voyageReferred=voyageReferred;
        this.status=reservation_status.waiting_payment;
        this.reservedRoom=room;
    }

}
