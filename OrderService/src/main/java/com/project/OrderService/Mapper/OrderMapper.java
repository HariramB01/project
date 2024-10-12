package com.project.OrderService.Mapper;

import com.eCommerce.basedomains.DTO.ProductDTO;
import com.project.OrderService.Client.InventoryServiceClient;
import com.eCommerce.basedomains.DTO.OrderDTO;
import com.project.OrderService.Entity.Order;
import com.project.OrderService.Event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

//    public Order convertOrderReqToOrder(OrderReq orderReq) {
//        Order order = new Order();
//        order.setUId(orderReq.getUId());
//        order.setItems(orderReq.getItems());
//        order.setOrderStatus(ORDER_STATUS.IN_PROGRESS);
//        order.setCreatedAt(LocalDateTime.now());
//        order.setUpdatedAt(LocalDateTime.now());
//        return order;
//    }

    public OrderDTO convertOrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUId(order.getUId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        return orderDTO;
    }

    public OrderEvent convertOrderToOrderEvent(Order order) {
        OrderEvent oe = new OrderEvent();
        oe.setId(order.getId());
        oe.setTotalAmount(order.getTotalAmount());
        oe.setUpdatedAt(order.getUpdatedAt());
        return oe;
    }

    public List<ProductDTO> convertListOfPIdsToListOfProducts(List<Long> items) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Long id : items){
            productDTOS.add(inventoryServiceClient.getProductById(id));
        }
        return productDTOS;
    }
}
