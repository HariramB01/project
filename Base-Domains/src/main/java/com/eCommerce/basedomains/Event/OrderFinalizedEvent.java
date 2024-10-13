package com.eCommerce.basedomains.Event;

import com.eCommerce.basedomains.DTO.OrderProduct;
import com.eCommerce.basedomains.DTO.OrderReq;

import java.util.List;

public class OrderFinalizedEvent {
    private boolean success;
    private OrderReq orderReq;
    private List<OrderProduct> finalizedProducts;

    // Constructors, getters, and setters

    public OrderFinalizedEvent(boolean success, OrderReq orderReq, List<OrderProduct> finalizedProducts) {
        this.success = success;
        this.orderReq = orderReq;
        this.finalizedProducts = finalizedProducts;
    }

    public boolean isSuccess() {
        return success;
    }

    public OrderReq getOrderReq() {
        return orderReq;
    }

    public List<OrderProduct> getFinalizedProducts() {
        return finalizedProducts;
    }
}

