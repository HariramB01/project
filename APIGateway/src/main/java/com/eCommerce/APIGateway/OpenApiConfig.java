package com.eCommerce.APIGateway;


import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class OpenApiConfig {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/swagger-config.json")
    public Map<String, Object> swaggerConfig() {
        Map<String, Object> config = new LinkedHashMap<>();
        List<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new LinkedList<>();

        discoveryClient.getServices().forEach(serviceName ->
                discoveryClient.getInstances(serviceName).forEach(serviceInstance -> {
                    AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
                    swaggerUrl.setName(serviceName); // Set the name of the service
                    swaggerUrl.setUrl(serviceInstance.getUri() + "/v3/api-docs"); // Set the URL for Swagger docs
                    urls.add(swaggerUrl);
                })
        );

        config.put("urls", urls);
        return config;
    }
}
