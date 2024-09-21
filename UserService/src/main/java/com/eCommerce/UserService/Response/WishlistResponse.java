package com.eCommerce.UserService.Response;

import com.eCommerce.UserService.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class WishlistResponse {

    private List<ProductDTO> products;

    public WishlistResponse() {
        products = new ArrayList<>();
    }

    public WishlistResponse(List<ProductDTO> products) {
        this.products = products;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "WishlistResponse{" +
                "products=" + products +
                '}';
    }
}
