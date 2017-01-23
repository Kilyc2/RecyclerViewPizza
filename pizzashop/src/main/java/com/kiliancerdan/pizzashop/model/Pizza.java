package com.kiliancerdan.pizzashop.model;

public class Pizza {

    private String name;
    private String image;
    private String description;

    public Pizza(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public Pizza(Pizza pizza) {
        this.name = pizza.name;
        this.image = pizza.image;
        this.description = pizza.description;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

}
