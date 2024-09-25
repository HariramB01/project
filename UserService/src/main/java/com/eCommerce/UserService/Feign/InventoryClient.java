package com.eCommerce.UserService.Feign;


import com.eCommerce.InventoryService.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8083/api/product")
public interface InventoryClient {

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);

}
