package com.project.NotificationService.Controller;


import com.project.NotificationService.Service.EmailService;
import com.project.OrderService.Event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    private void consumer(OrderEvent event){
        LOGGER.info(String.format("Order event received: %s", event.toString()));
        emailService.sendSimpleEmail(event);
    }

}
