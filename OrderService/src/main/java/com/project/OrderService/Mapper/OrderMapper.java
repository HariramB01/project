package com.project.OrderService.Mapper;

import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.project.OrderService.Client.InventoryServiceClient;
import com.project.OrderService.DTO.OrderDTO;
import com.project.OrderService.Entity.Order;
import com.project.OrderService.Enum.ORDER_STATUS;
import com.project.OrderService.Event.OrderEvent;
import com.project.OrderService.Request.OrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    public Order convertOrderReqToOrder(OrderReq orderReq) {
        Order order = new Order();
        order.setbId(orderReq.getbId());
        order.setuId(orderReq.getuId());
        order.setItems(orderReq.getItems());
        order.setOrderStatus(ORDER_STATUS.IN_PROGRESS);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }

    public OrderDTO convertOrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setuId(order.getId());
        orderDTO.setuId(order.getuId());
        orderDTO.setbId(order.getbId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        return orderDTO;
    }

    public OrderEvent convertOrderToOrderEvent(Order order) {
        OrderEvent oe = new OrderEvent();
        oe.setId(order.getId());
        oe.setbId(order.getbId());
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
