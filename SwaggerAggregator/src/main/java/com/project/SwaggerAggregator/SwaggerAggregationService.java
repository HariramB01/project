package com.project.SwaggerAggregator;

import com.project.FeignClient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SwaggerAggregationService {

    private final CartServiceClient cartServiceClient;
    private final ReviewServiceClient reviewServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final UserServiceClient userServiceClient;
    private final APIgatewayServiceClient apiGatewayClient;

    @Autowired
    public SwaggerAggregationService(CartServiceClient cartServiceClient,
                                     ReviewServiceClient reviewServiceClient,
                                     InventoryServiceClient inventoryServiceClient,
                                     UserServiceClient userServiceClient,
                                     APIgatewayServiceClient apiGatewayClient) {
        this.cartServiceClient = cartServiceClient;
        this.reviewServiceClient = reviewServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.userServiceClient = userServiceClient;
        this.apiGatewayClient = apiGatewayClient;
    }

    public Map<String, Object> aggregateApiDocs() {
        Map<String, Object> aggregatedDocs = new HashMap<>();

        try {
            aggregatedDocs.put("cart-service", cartServiceClient.getApiDocs());
            aggregatedDocs.put("review-service", reviewServiceClient.getApiDocs());
            aggregatedDocs.put("inventory-service", inventoryServiceClient.getApiDocs());
            aggregatedDocs.put("user-service", userServiceClient.getApiDocs());
            aggregatedDocs.put("api-gateway", apiGatewayClient.getApiDocs());
        } catch (Exception e) {
            // Handle the exception appropriately
            throw new RuntimeException("Failed to aggregate API docs", e);
        }

        return aggregatedDocs;
    }
}
