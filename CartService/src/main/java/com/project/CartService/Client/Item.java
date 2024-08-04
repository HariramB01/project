package com.project.CartService.Client;

public class Item {

    private String itemName;
    private double price;
    private String category;
    private String madeInLocation;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMadeInLocation() {
        return madeInLocation;
    }

    public void setMadeInLocation(String madeInLocation) {
        this.madeInLocation = madeInLocation;
    }
}
