package com.eCommerce.basedomains.DTO;


import com.eCommerce.basedomains.Enum.ORDER_STATUS;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long uId;
    private double totalAmount;
    private ORDER_STATUS orderStatus;
    private LocalDateTime updatedAt;

}
