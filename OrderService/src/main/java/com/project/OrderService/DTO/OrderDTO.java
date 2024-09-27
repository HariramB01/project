package com.project.OrderService.DTO;

import com.project.OrderService.Enum.ORDER_STATUS;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long uId;
    private Long bId;
    private double totalAmount;
    private ORDER_STATUS orderStatus;
    private LocalDateTime updatedAt;

    public OrderDTO() {
    }

    public OrderDTO(Long uId, Long bId, double totalAmount, ORDER_STATUS orderStatus, LocalDateTime updatedAt) {
        this.uId = uId;
        this.bId = bId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.updatedAt = updatedAt;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ORDER_STATUS getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(ORDER_STATUS orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "uId=" + uId +
                ", bId=" + bId +
                ", totalAmount=" + totalAmount +
                ", orderStatus=" + orderStatus +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
