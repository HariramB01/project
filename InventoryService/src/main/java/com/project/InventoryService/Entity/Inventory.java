package com.project.InventoryService.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Review_Table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int totalPieces;
    private LocalDateTime manufacturedDate;
    private LocalDateTime importedDate;
    private LocalDateTime itemCreatedDate;
    private double price;
    private String category;
    private String madeInLocation;
    private List<Long> ReviewId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(int totalPieces) {
        this.totalPieces = totalPieces;
    }

    public LocalDateTime getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(LocalDateTime manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public LocalDateTime getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(LocalDateTime importedDate) {
        this.importedDate = importedDate;
    }

    public LocalDateTime getItemCreatedDate() {
        return itemCreatedDate;
    }

    public void setItemCreatedDate(LocalDateTime itemCreatedDate) {
        this.itemCreatedDate = itemCreatedDate;
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

    public List<Long> getReviewId() {
        return ReviewId;
    }

    public void setReviewId(List<Long> reviewId) {
        ReviewId = reviewId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", totalPieces=" + totalPieces +
                ", manufacturedDate=" + manufacturedDate +
                ", importedDate=" + importedDate +
                ", itemCreatedDate=" + itemCreatedDate +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", madeInLocation='" + madeInLocation + '\'' +
                ", ReviewId=" + ReviewId +
                '}';
    }
}
