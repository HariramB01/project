package com.eCommerce.CartService.Client;


import com.eCommerce.basedomains.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8083/api/products")
public interface InventoryServiceClient {

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}
