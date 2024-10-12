package com.project.OrderService.Response;

import com.eCommerce.basedomains.DTO.ProductDTO;
import com.eCommerce.basedomains.Enum.ORDER_STATUS;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRes {

    private Long id;
    private Long uId;
    private List<ProductDTO> items;
    private double totalAmount;
    private ORDER_STATUS orderStatus;
    private LocalDateTime updatedAt;

}
