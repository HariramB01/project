package com.project.OrderService.Controller;

import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.project.OrderService.DTO.OrderDTO;
import com.project.OrderService.Entity.Order;
import com.project.OrderService.Event.OrderEvent;
import com.project.OrderService.KafkaProducer.OrderProducer;
import com.project.OrderService.Mapper.OrderMapper;
import com.project.OrderService.Request.OrderReq;
import com.project.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/makeOrder")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderReq orderReq) {
        List<ProductDTO> products = orderMapper.convertListOfPIdsToListOfProducts(orderReq.getItems());
        double price = 0;
        for (ProductDTO product : products) {
            price += product.getPrice();
        }
        Order order = orderService.createOrder(orderReq, products, price);
        if (order != null) {
            OrderDTO orderDTO = orderMapper.convertOrderToOrderDTO(order);
            OrderEvent orderEvent = orderMapper.convertOrderToOrderEvent(order);

//            String userEmail = orderReq.getUserEmail();
            orderEvent.setUserEmail("hariramb01@gmail.com");
            orderEvent.setSubject("Order Confirmation");
            orderEvent.setBody("Your order has been confirmed. Order ID: " + order.getId() + "Your ordered Items : "+ products.toString()+ " , Total AMount : "+ price);

            orderEvent.setItems(products);

            orderProducer.message(orderEvent);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }


}
