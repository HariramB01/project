package com.project.OrderService.Controller;

import com.eCommerce.basedomains.DTO.OrderReq;
import com.project.OrderService.Client.InventoryServiceClient;
import com.project.OrderService.Mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private static final String ORDER_EVENT_TOPIC = "order-event";

    private final KafkaTemplate<String, OrderReq> kafkaTemplate;

    @Autowired
    public OrderController(KafkaTemplate<String, OrderReq> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private InventoryServiceClient inventoryServiceClient;

//    @PostMapping("/makeOrder")
//    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderReq orderReq) {
//        List<ProductDTO> products = orderMapper.convertListOfPIdsToListOfProducts(orderReq.getItems());
//        double price = 0;
//        for (ProductDTO product : products) {
//            price += product.getPrice();
//        }
//        Order order = orderService.createOrder(orderReq, products, price);
//        if (order != null) {
//            OrderDTO orderDTO = orderMapper.convertOrderToOrderDTO(order);
//            OrderEvent orderEvent = orderMapper.convertOrderToOrderEvent(order);
//
////            String userEmail = orderReq.getUserEmail();
//            orderEvent.setUserEmail("hariramb01@gmail.com");
//            orderEvent.setSubject("Order Confirmation");
//            orderEvent.setBody("Your order has been confirmed. Order ID: " + order.getId() + "Your ordered Items : "+ products.toString()+ " , Total AMount : "+ price);
//
//            orderEvent.setItems(products);
//
//            orderProducer.message(orderEvent);
//            return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
//    }

    @PostMapping("/order")
    public ResponseEntity<Void> makeOrder(@RequestBody OrderReq orderReq) {
        try {
            logger.info("Sending OrderReq to Kafka: {}", orderReq.toString());

            kafkaTemplate.executeInTransaction(kafkaOperations -> {
                kafkaOperations.send(ORDER_EVENT_TOPIC, orderReq);
                return true;
            });

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Error sending OrderReq to Kafka", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
