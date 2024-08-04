package com.project.InventoryService.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class InventoryDTO {

    private Long id;

    @NotBlank(message = "Item name should not be empty")
    private String itemName;

    @Min(value = 1, message = "Total pieces must be 1 and above")
    private int totalPieces;

    @NotNull(message = "Manufactured date must not be null")
    @PastOrPresent(message = "Manufactured Date must be in the past or present")
    private LocalDateTime manufacturedDate;

    @NotNull(message = "Imported date must not be null")
    @PastOrPresent(message = "Imported Date must be in the past or present")
    private LocalDateTime importedDate;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double price;

    @NotBlank(message = "Category must not be empty")
    private String category;

    @NotBlank(message = "Made IN location must not be empty")
    private String madeInLocation;

    private List<Long> reviewId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Item name should not be empty") String getItemName() {
        return itemName;
    }

    public void setItemName(@NotBlank(message = "Item name should not be empty") String itemName) {
        this.itemName = itemName;
    }

    @Min(value = 1, message = "Total pieces must be 1 and above")
    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(@Min(value = 1, message = "Total pieces must be 1 and above") int totalPieces) {
        this.totalPieces = totalPieces;
    }

    public @NotNull(message = "Manufactured date must not be null") @PastOrPresent(message = "Manufactured Date must be in the past or present") LocalDateTime getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(@NotNull(message = "Manufactured date must not be null") @PastOrPresent(message = "Manufactured Date must be in the past or present") LocalDateTime manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public @NotNull(message = "Imported date must not be null") @PastOrPresent(message = "Imported Date must be in the past or present") LocalDateTime getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(@NotNull(message = "Imported date must not be null") @PastOrPresent(message = "Imported Date must be in the past or present") LocalDateTime importedDate) {
        this.importedDate = importedDate;
    }

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    public double getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "0.01", message = "Price must be greater than 0") double price) {
        this.price = price;
    }

    public @NotBlank(message = "Category must not be empty") String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank(message = "Category must not be empty") String category) {
        this.category = category;
    }

    public @NotBlank(message = "Made IN location must not be empty") String getMadeInLocation() {
        return madeInLocation;
    }

    public void setMadeInLocation(@NotBlank(message = "Made IN location must not be empty") String madeInLocation) {
        this.madeInLocation = madeInLocation;
    }

    public List<Long> getReviewId() {
        return reviewId;
    }

    public void setReviewId(List<Long> reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", totalPieces=" + totalPieces +
                ", manufacturedDate=" + manufacturedDate +
                ", importedDate=" + importedDate +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", madeInLocation='" + madeInLocation + '\'' +
                ", reviewId=" + reviewId +
                '}';
    }
}
