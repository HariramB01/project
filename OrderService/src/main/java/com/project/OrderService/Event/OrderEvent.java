package com.project.OrderService.Event;

import com.eCommerce.basedomains.DTO.ProductDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEvent {


    private Long id;
    private List<ProductDTO> items;
    private double totalAmount;
    private LocalDateTime updatedAt;
    private String userEmail;
    private String subject;
    private String body;

}
