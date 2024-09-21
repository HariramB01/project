package com.eCommerce.WishlistService.DTO;

import java.util.List;

public class CartDTO {
    private String cId;
    private List<ProductDTO> items;
    private double totalAmount;

    public CartDTO() {
    }

    public CartDTO(String cId, List<ProductDTO> items, double totalAmount) {
        this.cId = cId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
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

    @Override
    public String toString() {
        return "CartDTO{" +
                "cId='" + cId + '\'' +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
