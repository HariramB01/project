package com.eCommerce.UserService.DTO;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {

    private List<Long> productIds = new ArrayList<>();

    public Wishlist() {
    }

    public Wishlist(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "productIds=" + productIds +
                '}';
    }
}
