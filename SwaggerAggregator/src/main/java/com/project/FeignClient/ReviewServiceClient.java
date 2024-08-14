package com.project.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@FeignClient(name = "review-service", url = "http://localhost:8083")
public interface ReviewServiceClient {

    @GetMapping("/v3/api-docs")
    Map<String, Object> getApiDocs();
}
