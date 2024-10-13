package com.eCommerce.UserService.Feign;


import com.eCommerce.basedomains.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE", path = "/api/product")
public interface InventoryClient {

    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);

}
