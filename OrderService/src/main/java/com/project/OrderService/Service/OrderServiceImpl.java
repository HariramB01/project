package com.project.OrderService.Service;

import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.project.OrderService.Entity.Order;
import com.project.OrderService.Enum.ORDER_STATUS;
import com.project.OrderService.Mapper.OrderMapper;
import com.project.OrderService.Repository.OrderRepository;
import com.project.OrderService.Request.OrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order createOrder(OrderReq orderReq, List<ProductDTO> products, double price) {
        Order order = orderMapper.convertOrderReqToOrder(orderReq);
        order.setOrderStatus(ORDER_STATUS.IN_PROGRESS);
        order.setTotalAmount(price);
        orderRepository.save(order);
        return order;
    }
}
