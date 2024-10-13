package com.eCommerce.CartService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long uId;

    @ElementCollection
    private List<Long> productIds = new ArrayList<>();

    @PastOrPresent(message = "Creation date cannot be in the future")
    private LocalDateTime createdAt;

    @PastOrPresent(message = "Update date cannot be in the future")
    private LocalDateTime updatedAt;

    private double totalAmount;

    public void addProduct(Long productId) {
        this.productIds.add(productId);
        this.updatedAt = LocalDateTime.now();
    }
}
