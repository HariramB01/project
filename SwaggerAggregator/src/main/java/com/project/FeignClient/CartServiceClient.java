package com.project.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@FeignClient(name = "cart-service", url = "http://localhost:8085")
public interface CartServiceClient {
    @GetMapping("/v3/api-docs")
    Map<String, Object> getApiDocs();
}
