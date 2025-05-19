package com.example.demo1;
// demonstration data
public class PackageGUI implements DisplayableItem{
    private int id;
    private String name;
    private String description;
    private double price;

    public PackageGUI(int id, String name, String description, double price) {
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
    public static PackageGUI[] getPackages() {
        return new PackageGUI[]{
                new PackageGUI(1, "Package 1", "Description 1",45.06),
                new PackageGUI(2, "Package 2", "Description 2",38.02),
                new PackageGUI(3, "Package 3", "Description 3",767.03),
                new PackageGUI(4, "Package 4", "Description 4",890.06)
        };
    }
}
