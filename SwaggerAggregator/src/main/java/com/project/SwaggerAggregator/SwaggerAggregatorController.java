package com.project.SwaggerAggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
@RestController
@RequestMapping("/swagger-aggregator")
public class SwaggerAggregatorController {

    private final SwaggerAggregationService swaggerAggregationService;

    @Autowired
    public SwaggerAggregatorController(SwaggerAggregationService swaggerAggregationService) {
        this.swaggerAggregationService = swaggerAggregationService;
    }

    @GetMapping("/v3/api-docs")
    public Map<String, Object> getAggregatedApiDocs() {
        try {
            return swaggerAggregationService.aggregateApiDocs();
        } catch (Exception e) {
            // Return an error response or handle the exception as needed
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to aggregate API docs", e);
        }
    }
}
