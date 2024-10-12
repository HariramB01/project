package com.project.NotificationService.Controller;

import com.eCommerce.basedomains.Event.StockDeductedEvent;
import com.project.NotificationService.Service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-status-topic-success", groupId = "notification-group")
    public ResponseEntity<String> sendSuccessEmail(StockDeductedEvent stockDeductedEvent) {
        LOGGER.info("Payment success event received: {}", stockDeductedEvent);

        try {
            emailService.sendEmail(stockDeductedEvent);
            LOGGER.info("Success email sent for event: {}", stockDeductedEvent);
            return ResponseEntity.ok("Success email sent");
        } catch (Exception e) {
            LOGGER.error("Failed to send success email for event: {}. Error: {}", stockDeductedEvent, e.getMessage());
            return ResponseEntity.status(500).body("Failed to send success email");
        }
    }

    @KafkaListener(topics = "payment-status-topic-failure", groupId = "notification-group")
    public ResponseEntity<String> sendFailureEmail(StockDeductedEvent stockDeductedEvent) {
        LOGGER.info("Payment failure event received: {}", stockDeductedEvent);

        try {
            emailService.sendEmail(stockDeductedEvent);
            LOGGER.info("Failure email sent for event: {}", stockDeductedEvent);
            return ResponseEntity.ok("Failure email sent");
        } catch (Exception e) {
            LOGGER.error("Failed to send failure email for event: {}. Error: {}", stockDeductedEvent, e.getMessage());
            return ResponseEntity.status(500).body("Failed to send failure email");
        }
    }
}
