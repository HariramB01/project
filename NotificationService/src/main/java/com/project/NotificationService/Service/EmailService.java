package com.project.NotificationService.Service;

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

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendSimpleEmail(OrderEvent event) {
        logger.info("Email service", event.toString());
        try {
            String fromEmail = "bhariram01@gmail.com";

            Email email = new Email();
            email.setFromEmail(fromEmail);
            email.setToEmail(event.getUserEmail());
            email.setSubject(event.getSubject());
            email.setBody(event.getBody());

            emailRepository.save(email);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(event.getUserEmail());
            message.setSubject(event.getSubject());
            message.setText(event.getBody());
            message.setFrom(fromEmail);

            mailSender.send(message);
            System.out.println("Mail sent successfully from " + fromEmail);
            System.out.println("Successfully mail sent :"+event.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
