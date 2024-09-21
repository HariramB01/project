package com.eCommerce.CartService.Response;

import com.eCommerce.CartService.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class CartResponse {

    private Long id;
    private Long uId;
    private double totalAmount;
    private List<ProductDTO> products;

    public CartResponse() {
    }

    public CartResponse(Long id, Long uId, double totalAmount, List<ProductDTO> products) {
        this.id = id;
        this.uId = uId;
        this.totalAmount = totalAmount;
        this.products = new ArrayList<>();
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", uId=" + uId +
                ", totalAmount=" + totalAmount +
                ", products=" + products +
                '}';
    }
}
