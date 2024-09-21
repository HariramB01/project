package com.eCommerce.UserService.Feign;


import com.eCommerce.UserService.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8083/api/product")
public interface InventoryClient {

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);

}
