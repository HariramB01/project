package com.project.OrderService.Response;

import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.project.OrderService.Enum.ORDER_STATUS;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRes {

    private Long id;
    private Long uId;
    private Long bId;
    private List<ProductDTO> items;
    private double totalAmount;
    private ORDER_STATUS orderStatus;
    private LocalDateTime updatedAt;

    public OrderRes() {
    }

    public OrderRes(Long id, Long uId, Long bId, List<ProductDTO> items, double totalAmount, ORDER_STATUS orderStatus, LocalDateTime updatedAt) {
        this.id = id;
        this.uId = uId;
        this.bId = bId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "OrderRes{" +
                "id=" + id +
                ", uId=" + uId +
                ", bId=" + bId +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                ", orderStatus=" + orderStatus +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
