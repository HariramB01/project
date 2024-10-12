package com.eCommerce.PaymentService;

import com.eCommerce.basedomains.Event.StockDeductedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final String PAYMENT_SUCCESS_TOPIC = "payment-status-topic-success";
    private static final String PAYMENT_FAILURE_TOPIC = "payment-status-topic-failure";
    private static final String STOCK_DEDUCTED_FAILURE_TOPIC = "stock-deducted-topic-failure";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    public PaymentService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @KafkaListener(topics = "stock-deducted-topic-success", groupId = "payment-group")
//    public ResponseEntity<String> paymentProcess(StockDeductedEvent stockDeductedEvent) {
//        logger.info("Received stock deducted event: {}", stockDeductedEvent);
//
//        if (stockDeductedEvent.isResult()) {
//            kafkaTemplate.send(PAYMENT_SUCCESS_TOPIC, stockDeductedEvent);
//            logger.info("Payment processed successfully for event: {}", stockDeductedEvent);
//            return ResponseEntity.ok("Payment processed successfully");
//        } else {
//            kafkaTemplate.send(PAYMENT_FAILURE_TOPIC, stockDeductedEvent);
//            logger.error("Payment processing failed for event: {}", stockDeductedEvent);
//            return ResponseEntity.status(400).body("Payment processing failed");
//        }
//    }


    @KafkaListener(topics = "stock-deducted-topic-success", groupId = "payment-group")
    public ResponseEntity<String> paymentProcess(StockDeductedEvent stockDeductedEvent) {
        logger.info("Received stock deducted event: {}", stockDeductedEvent);

        try {
            // Simulate payment processing
//            boolean paymentSuccess = processPayment(stockDeductedEvent);
            boolean paymentSuccess = true;
            if (paymentSuccess) {
                kafkaTemplate.send(PAYMENT_SUCCESS_TOPIC, stockDeductedEvent);
                logger.info("Payment processed successfully for event: {}", stockDeductedEvent);
                return ResponseEntity.ok("Payment processed successfully");
            } else {
                kafkaTemplate.send(PAYMENT_FAILURE_TOPIC, stockDeductedEvent);
                logger.error("Payment processing failed for event: {}", stockDeductedEvent);

                // Publish event to revert stock if payment fails
                kafkaTemplate.send(STOCK_DEDUCTED_FAILURE_TOPIC, stockDeductedEvent);
                return ResponseEntity.status(400).body("Payment processing failed");
            }
        } catch (Exception e) {
            logger.error("Error processing payment: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Error processing payment");
        }
    }


}
