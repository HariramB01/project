package com.project.OrderService.Service;

import com.eCommerce.basedomains.DTO.OrderProduct;
import com.eCommerce.basedomains.Enum.ORDER_STATUS;
import com.eCommerce.basedomains.Event.StockDeductedEvent;
import com.project.OrderService.Entity.Order;
import com.project.OrderService.Mapper.OrderMapper;
import com.project.OrderService.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @KafkaListener(topics = {"stock-deducted-topic-failure", "payment-status-topic-failure"}, groupId = "inventory_group")
    public void noneAvailable(StockDeductedEvent stockDeductedEvent) {
        logger.info("Order failure: {}", stockDeductedEvent.toString());
        // Rollback or compensating logic can be implemented here if needed
    }

    @KafkaListener(topics = "payment-status-topic-success", groupId = "order-success-group")
    public void orderStatusUpdate(StockDeductedEvent stockDeductedEvent) {
        logger.info("Received order success event: {}", stockDeductedEvent.toString());
        Order order = new Order();
        order.setOrderStatus(ORDER_STATUS.COMPLETED);
        order.setItems(stockDeductedEvent.getOrderReq().getItems());
        order.setUpdatedAt(LocalDateTime.now());
        double totalAmount = stockDeductedEvent.getOrderReq().getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);
        order.setUId(stockDeductedEvent.getOrderReq().getUId());

        try {
            orderRepository.save(order);
            logger.info("Order saved successfully: {}", order.toString());
        } catch (Exception e) {
            logger.error("Failed to save order: {}. Error: {}", order, e.getMessage());
        }
    }


//    @Override
//    public Order createOrder(OrderReq orderReq, List<ProductDTO> products, double price) {
//        Order order = orderMapper.convertOrderReqToOrder(orderReq);
//        order.setTotalAmount(price);
//        logger.info("Print order : {}", order.toString());
//        orderRepository.save(order);
//        return order;
//    }
}
