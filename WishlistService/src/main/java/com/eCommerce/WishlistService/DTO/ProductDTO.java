package com.eCommerce.WishlistService.DTO;

public class ProductDTO {
    private String pId;
    private String name;
    private CATEGORY category;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(String pId, String name, CATEGORY category, double price) {
        this.pId = pId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "pId='" + pId + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
