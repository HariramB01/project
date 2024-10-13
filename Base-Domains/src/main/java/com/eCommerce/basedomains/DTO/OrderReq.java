package com.eCommerce.basedomains.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderReq {
    private Long uId;
    private List<OrderProduct> items;

}
