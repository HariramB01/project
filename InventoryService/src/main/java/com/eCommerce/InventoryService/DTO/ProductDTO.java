package com.eCommerce.InventoryService.DTO;


import com.eCommerce.InventoryService.Enum.AVAILABILITY_STATUS;
import com.eCommerce.InventoryService.Enum.CATEGORY;

import java.time.LocalDate;

public class ProductDTO {
    private Long id;
    private String productName;
    private int quantity;
    private double price;
    private AVAILABILITY_STATUS availabilityStatus;
    private CATEGORY category;
    private LocalDate createdAt;
    private LocalDate expiryDate;

    public ProductDTO() {}

    public ProductDTO(Long id,String productName, int quantity, double price, AVAILABILITY_STATUS availabilityStatus, CATEGORY category, LocalDate createdAt, LocalDate expiryDate) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
        this.category = category;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AVAILABILITY_STATUS getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AVAILABILITY_STATUS availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", availabilityStatus=" + availabilityStatus +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
