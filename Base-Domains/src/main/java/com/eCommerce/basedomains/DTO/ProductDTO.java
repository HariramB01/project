package com.eCommerce.basedomains.DTO;


import com.eCommerce.basedomains.Enum.AVAILABILITY_STATUS;
import com.eCommerce.basedomains.Enum.CATEGORY;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private int quantity;
    private double price;
    private AVAILABILITY_STATUS availabilityStatus;
    private CATEGORY category;
    private LocalDate createdAt;
    private LocalDate expiryDate;
}
