package com.project.APIgateway.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final RouteValidator routeValidator;

    public SecurityConfig(RouteValidator routeValidator) {
        this.routeValidator = routeValidator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf
                        .requireCsrfProtectionMatcher(new NegatedServerWebExchangeMatcher(
                                ServerWebExchangeMatchers.pathMatchers("/user/register", "/user/token", "/user/validate")))
                        .disable()
                )
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(routeValidator.openApiEndpoints.toArray(new String[0])).permitAll()
                        .anyExchange().authenticated()
                )
                .build();
    }
}
