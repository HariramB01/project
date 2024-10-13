package com.eCommerce.UserService.Feign;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

//@LoadBalancerClient(value = "WISHLIST-SERVICE")
public class WishlistServiceLoadBalancer {

//    @Bean(name = "wishlistFeignBuilder")
//    @LoadBalanced
//    public Feign.Builder feiBuilder() {
//        return Feign.builder();
//    }
}
