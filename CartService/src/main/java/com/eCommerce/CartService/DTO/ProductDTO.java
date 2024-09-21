package com.eCommerce.CartService.DTO;

import com.eCommerce.CartService.Enum.CATEGORY;

import java.time.LocalDate;

public class ProductDTO {
    private Long id;
    private String productName;
    private CATEGORY category;
    private double price;
    private int quantity;
    private LocalDate createdAt;
    private LocalDate expiryDate;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String productName, CATEGORY category, double price, int quantity, LocalDate createdAt, LocalDate expiryDate) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return productName;
    }

    public void setName(String productName) {
        this.productName = productName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
