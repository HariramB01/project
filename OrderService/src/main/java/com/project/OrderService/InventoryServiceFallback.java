package com.project.OrderService;

import com.eCommerce.basedomains.DTO.ProductDTO;
import com.eCommerce.basedomains.DTO.OrderProduct;
import com.project.OrderService.Client.InventoryServiceClient;
import org.springframework.stereotype.Component;

@Component
public class InventoryServiceFallback implements InventoryServiceClient {

    @Override
    public ProductDTO getProductById(Long id) {
        // Handle fallback logic here
        return new ProductDTO();  // Return a default or null product
    }

    @Override
    public boolean checkProductQuantity(OrderProduct product) {
        // Handle fallback logic here
        return false;  // Assume quantity check failed
    }
}