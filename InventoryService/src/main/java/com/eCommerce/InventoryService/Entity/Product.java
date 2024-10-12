package com.eCommerce.InventoryService.Entity;

import com.eCommerce.basedomains.Enum.AVAILABILITY_STATUS;
import com.eCommerce.basedomains.Enum.CATEGORY;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Data
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

    @NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String productName;

    @Min(value = 0, message = "Quantity cannot be less than 0")
    private int quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.expiryDate = LocalDate.now().plusYears(1);
    }
}
