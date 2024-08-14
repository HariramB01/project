package com.project.FeignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@FeignClient(name = "api-gateway", url = "http://localhost:8989")
public interface APIgatewayServiceClient {

    @GetMapping("/v3/api-docs")
    Map<String, Object> getApiDocs();
}
