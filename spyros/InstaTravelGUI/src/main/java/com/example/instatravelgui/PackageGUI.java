package com.example.instatravelgui;
// Assistant Screen so I can populate table with raw data
public class PackageGUI {

        private String id;
        private String name;
        private String description;
        private String price;

        public PackageGUI(String id, String name, String description, String price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getPrice() {
            return price;
        }

}
