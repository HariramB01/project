package com.eCommerce.basedomains.DTO;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {

    private List<Long> productIds = new ArrayList<>();
    private double totalAmount;

}
