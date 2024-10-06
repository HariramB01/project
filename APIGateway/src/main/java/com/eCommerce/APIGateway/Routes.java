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
                .route("user-service", r -> r.path("/api/v1/user/**", "/api/address/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("userServiceCircuitBreaker")
                                        .setFallbackUri("forward:/userServiceFallback")))
                        .uri("lb://USER-SERVICE"))
                .route("cart-service", r -> r.path("/api/cart/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("cartServiceCircuitBreaker")
                                        .setFallbackUri("forward:/cartServiceFallback")))
                        .uri("lb://CART-SERVICE"))
                .route("inventory-service", r -> r.path("/api/inventory/**", "/api/products/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("inventoryServiceCircuitBreaker")
                                        .setFallbackUri("forward:/inventoryServiceFallback")))
                        .uri("lb://INVENTORY-SERVICE"))
                .route("order-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("orderServiceCircuitBreaker")
                                        .setFallbackUri("forward:/orderServiceFallback")))
                        .uri("lb://ORDER-SERVICE"))
                .route("notification-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("notificationServiceCircuitBreaker")
                                        .setFallbackUri("forward:/notificationServiceFallback")))
                        .uri("lb://NOTIFICATION-SERVICE"))
                .route("security-service", r -> r.path("/api/security/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("securityServiceCircuitBreaker")
                                        .setFallbackUri("forward:/securityServiceFallback")))
                        .uri("lb://SECURITY-SERVICE"))
                .route("wishlist-service", r -> r.path("/api/wishlist/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                .circuitBreaker(config -> config
                                        .setName("wishlistServiceCircuitBreaker")
                                        .setFallbackUri("forward:/wishlistServiceFallback")))
                        .uri("lb://WISHLIST-SERVICE"))
                .build();
    }
}
