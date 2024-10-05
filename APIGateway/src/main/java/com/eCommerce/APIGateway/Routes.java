package com.eCommerce.APIGateway;

import com.eCommerce.APIGateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Autowired
    private AuthenticationFilter authenticationFilter;

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("user-service", r -> r.path("/api/v1/user/**", "/api/address/**")
//                        .uri("lb://USER-SERVICE"))
//                .route("cart-service", r -> r.path("/api/cart/**")
//                        .uri("lb://CART-SERVICE"))
//                .route("inventory-service", r -> r.path("/api/inventory/**", "/api/products/**")
//                        .uri("lb://INVENTORY-SERVICE"))
//                .route("order-service", r -> r.path("/api/order/**")
//                        .uri("lb://ORDER-SERVICE"))
//                .route("notification-service", r -> r.path("/api/order/**")
//                        .uri("lb://NOTIFICATION-SERVICE"))
//                .route("security-service", r -> r.path("/api/security/**")
//                        .uri("lb://SECURITY-SERVICE"))
//                .route("wishlist-service", r -> r.path("/api/wishlist/**")
//                        .uri("lb://WISHLIST-SERVICE"))
//                .build();
//    }
}
