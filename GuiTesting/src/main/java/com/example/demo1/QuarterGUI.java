package com.example.demo1;

public class QuarterGUI implements DisplayableItem{
    private int id;
    private String name;
    private String description;
    private double price;

    public QuarterGUI(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public static QuarterGUI[] getPackages() {
        return new QuarterGUI[]{
                new QuarterGUI(1, "Quarter 1", "Description 1",45.06),
                new QuarterGUI(2, "Quarter 2", "Description 2",38.02),
                new QuarterGUI(3, "Quarter 3", "Description 3",767.03),
                new QuarterGUI(4, "Quarter 4", "Description 4",890.06)
        };
    }
}
