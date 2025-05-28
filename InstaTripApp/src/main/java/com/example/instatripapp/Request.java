package com.example.instatripapp;

import java.util.Map;

enum RequestStatus{
    active,
    pending,
    canceled;

    public static RequestStatus fromString(String value){
        Map<String, RequestStatus> statusMap = Map.of(
                "Σε αναμονή", pending,
                "Σε Αναμονή", pending,
                "Σε ισχύ", active,
                "Ακυρωμένη", canceled
        );
        return statusMap.get(value);
    }

    public static String toString(RequestStatus status){
        Map<RequestStatus, String> statusStringMap =Map.of(
                pending, "Σε αναμονή",
                active,  "Σε ισχύ",
                canceled, "Ακυρωμένη"
        );
        return statusStringMap.get(status);
    }
}

public class Request implements Searchable{
    private String cooperatorName;
    private long packageID;
    private RequestStatus status;
    private long requestID;
    private long agencyID;
    private partnerType ptype;
    private String name;



    public Request(String cooperatorName,long packageID, RequestStatus status, long requestID, long agencyID, partnerType ptype){
        this.cooperatorName=cooperatorName;
        this.requestID=requestID;
        this.packageID=packageID;
        this.status=status;
        this.ptype=ptype;
        this.agencyID=agencyID;
    }

    public Request(String name, long packageID, RequestStatus status, long requestID){
        this.name=name;
        this.packageID=packageID;
        this.status=status;
        this.requestID=requestID;

    }


    @Override
    public long getKey() {
        return requestID;
    }

    public long getRequestID(){
        return requestID;
    }

    public String getCooperatorName(){
        return cooperatorName;
    }

    public long getPackageID(){
        return packageID;
    }

    public String getStatus(){
        return RequestStatus.toString(status);
    }

    public long getAgencyID(){
        return agencyID;
    }

    public String getPtype(){
        return partnerType.toString(ptype);
    }
    public String getName(){return name;}
}
