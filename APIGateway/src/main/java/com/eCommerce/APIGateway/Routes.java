package com.eCommerce.APIGateway;

import com.eCommerce.APIGateway.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    private final AuthenticationFilter authenticationFilter;

    public Routes(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service Route
                .route("user-service", r -> r.path("/api/v1/user/**", "/api/address/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("userServiceCircuitBreaker")
                                        .setFallbackUri("forward:/userServiceFallback")))
                        .uri("lb://USER-SERVICE"))

                // Cart Service Route
                .route("cart-service", r -> r.path("/api/cart/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("cartServiceCircuitBreaker")
                                        .setFallbackUri("forward:/cartServiceFallback")))
                        .uri("lb://CART-SERVICE"))

                // Inventory Service Route
                .route("inventory-service", r -> r.path("/api/inventory/**", "/api/products/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("inventoryServiceCircuitBreaker")
                                        .setFallbackUri("forward:/inventoryServiceFallback")))
                        .uri("lb://INVENTORY-SERVICE"))

                // Order Service Route
                .route("order-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("orderServiceCircuitBreaker")
                                        .setFallbackUri("forward:/orderServiceFallback")))
                        .uri("lb://ORDER-SERVICE"))

                // Notification Service Route
                .route("notification-service", r -> r.path("/api/notification/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("notificationServiceCircuitBreaker")
                                        .setFallbackUri("forward:/notificationServiceFallback")))
                        .uri("lb://NOTIFICATION-SERVICE"))

                // Security Service Route
                .route("security-service", r -> r.path("/api/security/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("securityServiceCircuitBreaker")
                                        .setFallbackUri("forward:/securityServiceFallback")))
                        .uri("lb://SECURITY-SERVICE"))

                // Wishlist Service Route
                .route("wishlist-service", r -> r.path("/api/wishlist/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("wishlistServiceCircuitBreaker")
                                        .setFallbackUri("forward:/wishlistServiceFallback")))
                        .uri("lb://WISHLIST-SERVICE"))

                .route("swagger_user_service", r -> r.path("/api-docs/user")
                        .uri("http://localhost:8081/api-docs"))
                .route("swagger_address_service", r -> r.path("/api-docs/address")
                        .uri("http://localhost:8082/api-docs"))
                .route("swagger_cart_service", r -> r.path("/api-docs/cart")
                        .uri("http://localhost:8083/api-docs"))
                .route("swagger_inventory_service", r -> r.path("/api-docs/inventory")
                        .uri("http://localhost:8084/api-docs"))
                .build();
    }
}
