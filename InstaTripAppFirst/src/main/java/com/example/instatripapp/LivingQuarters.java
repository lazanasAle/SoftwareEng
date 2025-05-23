package com.example.instatripapp;

import java.util.*;

class LivingQuarter extends ExtPartner {
    protected HashSet<Room> rooms;


    LivingQuarter(Long partner_id, String partnerName, String addressName, String location, String schedule, String phone, String email, String description, partnerType ptype){
        super(partner_id, partnerName, addressName, location, schedule, phone, email, description, ptype);
        rooms=new HashSet<>();
    }


}

class Room {

}



