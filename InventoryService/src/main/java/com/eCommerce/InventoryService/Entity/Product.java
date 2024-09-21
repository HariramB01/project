package com.eCommerce.InventoryService.Entity;

import com.eCommerce.InventoryService.Enum.AVAILABILITY_STATUS;
import com.eCommerce.InventoryService.Enum.CATEGORY;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "Product")
@JsonIgnoreProperties({"inventory"})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    @JsonBackReference
    private Inventory inventory;

    private String productName;
    private int quantity;
    private double price;

    @Enumerated(EnumType.STRING)
    private AVAILABILITY_STATUS availabilityStatus;

    @Enumerated(EnumType.STRING)
    private CATEGORY category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    public Product() {
        this.createdAt = LocalDate.now();
        this.expiryDate = LocalDate.now().plusYears(1);
    }

    // Constructors, Getters, and Setters...

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.expiryDate = LocalDate.now().plusYears(1);
    }

    public Product(Long id, Inventory inventory, String productName, int quantity, double price,
                   AVAILABILITY_STATUS availabilityStatus, CATEGORY category) {
        this.id = id;
        this.inventory = inventory;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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
        return "Product{" +
                "id=" + id +
                ", inventory=" + inventory +
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
