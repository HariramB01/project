package com.project.OrderService.Entity;

import com.eCommerce.basedomains.DTO.OrderProduct;
import com.eCommerce.basedomains.Enum.ORDER_STATUS;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long uId;

    @ElementCollection
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderProduct> items = new ArrayList<>();

    @PositiveOrZero(message = "Total amount must be zero or positive")
    private double totalAmount;

    @NotNull(message = "Order status cannot be null")
    @Enumerated(EnumType.STRING)
    private ORDER_STATUS orderStatus;

    @PastOrPresent(message = "Created date cannot be in the future")
    private LocalDateTime createdAt;

    @PastOrPresent(message = "Updated date cannot be in the future")
    private LocalDateTime updatedAt;
}
