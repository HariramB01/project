package com.project.NotificationService.Service;

import com.eCommerce.basedomains.Event.StockDeductedEvent;
import com.eCommerce.basedomains.DTO.UserNotificationDTO;
import com.project.NotificationService.Client.UserServiceClient;
import com.project.NotificationService.Entity.Email;
import com.project.NotificationService.Repository.EmailRepository;
import com.project.OrderService.Event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendSimpleEmail(OrderEvent event) {
        logger.info("Initiating email sending for OrderEvent: {}", event);
        try {
            String fromEmail = "bhariram01@gmail.com";

            Email email = new Email();
            email.setFromEmail(fromEmail);
            email.setToEmail(event.getUserEmail());
            email.setSubject(event.getSubject());
            email.setBody(event.getBody());

            emailRepository.save(email);
            logger.debug("Saved email to repository: {}", email);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(event.getUserEmail());
            message.setSubject(event.getSubject());
            message.setText(event.getBody());
            message.setFrom(fromEmail);

            mailSender.send(message);
            logger.info("Mail sent successfully to {} from {}", event.getUserEmail(), fromEmail);
        } catch (Exception e) {
            logger.error("Error occurred while sending email for OrderEvent: {}", event, e);
        }
    }

    public void sendEmail(StockDeductedEvent stockDeductedEvent) {
        logger.info("Preparing to send email for StockDeductedEvent: {}", stockDeductedEvent);
        try {
            String fromEmail = "bhariram01@gmail.com";

            Email email = new Email();
            email.setFromEmail(fromEmail);

            UserNotificationDTO userNotificationDTO = userServiceClient.getUsernameAndEmail(stockDeductedEvent.getOrderReq().getUId()).getBody();
            if (userNotificationDTO == null) {
                logger.warn("No UserNotificationDTO found for User ID: {}", stockDeductedEvent.getOrderReq().getUId());
                return;
            }

            String toEmail = userNotificationDTO.getEmail();
            String subject = "Your User Id: " + stockDeductedEvent.getOrderReq().getUId();
            String body = "Hello " + userNotificationDTO.getUsername() + ", your ordered products " + stockDeductedEvent.getOrderReq().getItems() + " will be delivered soon.";
            email.setToEmail(toEmail);
            email.setSubject(subject);
            email.setBody(body);

            emailRepository.save(email);
            logger.debug("Saved email to repository: {}", email);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            logger.info("Mail sent successfully to {} from {}", toEmail, fromEmail);
        } catch (Exception e) {
            logger.error("Error occurred while sending email for StockDeductedEvent: {}", stockDeductedEvent, e);
        }
    }
}
