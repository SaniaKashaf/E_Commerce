package com.example.ecommerce;

public class Category {

        private String name,icon,color,brief;
        private int id;

    public Category(String name, String icon, String color, String brief, int id) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.brief = brief;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getColor() {
        return color;
    }

    public String getBrief() {
        return brief;
    }

    public int getId() {
        return id;
    }
}
