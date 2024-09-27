package com.project.OrderService.Event;

import com.eCommerce.InventoryService.DTO.ProductDTO;

import java.time.LocalDateTime;
import java.util.List;

public class OrderEvent {


    private Long id;
    private Long bId;
    private List<ProductDTO> items;
    private double totalAmount;
    private LocalDateTime updatedAt;
    private String userEmail;
    private String subject;
    private String body;

    public OrderEvent() {
    }

    public OrderEvent(Long id, Long bId, List<ProductDTO> items, double totalAmount, LocalDateTime updatedAt, String userEmail, String subject, String body) {
        this.id = id;
        this.bId = bId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.updatedAt = updatedAt;
        this.userEmail = userEmail;
        this.subject = subject;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public List<ProductDTO> getItems() {
        return items;
    }

    public void setItems(List<ProductDTO> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
