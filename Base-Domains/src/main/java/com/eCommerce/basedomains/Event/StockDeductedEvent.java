package com.eCommerce.basedomains.Event;

import com.eCommerce.basedomains.DTO.OrderReq;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDeductedEvent {

    private boolean result;
    private OrderReq orderReq;

}
