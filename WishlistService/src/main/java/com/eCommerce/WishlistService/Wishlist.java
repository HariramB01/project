package com.eCommerce.WishlistService;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uId;
    @ElementCollection
    private List<Long> productIds = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Wishlist() {
    }

    public Wishlist(Long id, Long uId, List<Long> productIds, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.uId = uId;
        this.productIds = productIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addProduct(Long productId) {
        this.productIds.add(productId);
        this.updatedAt = LocalDateTime.now();  // Update the updatedAt timestamp
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                "uId=" + uId +
                ", productIds=" + productIds +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
