package com.example.instatravelgui;
// It is more like accomodation
public class QuarterGUI {
    private String apartmentName;
    private String region;
    private String apartmentType;

    public QuarterGUI(String apartmentName, String region, String apartmentType) {
        this.apartmentName = apartmentName;
        this.region = region;
        this.apartmentType = apartmentType;

    }
    public String getApartmentName() {
        return apartmentName;
    }
    public String getRegion() {
        return region;
    }
    public String getApartmentType() {
        return apartmentType;
    }
}
