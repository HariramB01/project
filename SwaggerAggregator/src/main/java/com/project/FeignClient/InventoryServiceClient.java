package com.project.FeignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@FeignClient(name = "inventory-service", url = "http://localhost:8082")
public interface InventoryServiceClient {

    @GetMapping("/v3/api-docs")
    Map<String, Object> getApiDocs();
}
