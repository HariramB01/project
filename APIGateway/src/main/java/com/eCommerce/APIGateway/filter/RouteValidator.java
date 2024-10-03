package com.eCommerce.APIGateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private static final Logger logger = LoggerFactory.getLogger(RouteValidator.class);

    public static final List<String> openApiEndpoints = List.of(
            "/api/security/create",
            "/api/security/signin",
            "/api/security/hello",
            "/api/v1/user/register"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> {
        String requestPath = request.getURI().getPath();
        logger.info("Checking request path: " + requestPath);

        boolean result = openApiEndpoints.stream()
                .noneMatch(uri -> requestPath.contains(uri));

        logger.info("Is request secured: " + result);
        return result;
    };
}