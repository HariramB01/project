package com.project.OrderService.Client;


import com.eCommerce.basedomains.DTO.ProductDTO;
import com.eCommerce.basedomains.DTO.OrderProduct;
import com.project.OrderService.InventoryServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


<<<<<<< HEAD
@FeignClient(name = "inventory-service", url = "http://localhost:8083/api/products")
=======
@FeignClient(name = "inventory-service", fallback = InventoryServiceFallback.class)
>>>>>>> b253af3 (Cloud config & EDA)
public interface InventoryServiceClient {

    @GetMapping("/api/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);

    @PostMapping("/api/products/check-quantity")
    boolean checkProductQuantity(@RequestBody OrderProduct product);
}