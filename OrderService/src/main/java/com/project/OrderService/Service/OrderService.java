package com.project.OrderService.Service;

import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.project.OrderService.Entity.Order;
import com.project.OrderService.Request.OrderReq;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderReq orderReq, List<ProductDTO> products, double price);
}
