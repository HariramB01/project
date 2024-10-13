package com.eCommerce.basedomains.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProduct {
    private Long id;
    private String productName;
    private int quantity;
    private double price;
}
