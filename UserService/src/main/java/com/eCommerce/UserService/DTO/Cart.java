package com.eCommerce.UserService.DTO;


import java.util.ArrayList;
import java.util.List;

public class Cart {


    private List<Long> productIds = new ArrayList<>();
    private double totalAmount;

    public Cart() {
    }

    public Cart(List<Long> productIds, double totalAmount) {
        this.productIds = productIds;
        this.totalAmount = totalAmount;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productIds=" + productIds +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
