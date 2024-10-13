package com.eCommerce.UserService.Feign;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

//@LoadBalancerClient(value = "CART-SERVICE")
public class CartServiceLoadBalancer {

//    @Bean(name = "cartFeignBuilder")
//    @LoadBalanced
//    public Feign.Builder feiBuilder() {
//        return Feign.builder();
//    }
}
