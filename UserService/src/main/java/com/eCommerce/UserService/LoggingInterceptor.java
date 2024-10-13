package com.eCommerce.UserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String ipAddress = getIpAddress();
        System.out.println("Request from: " + appName + " - IP: " + ipAddress + " - URI: " + request.getRequestURI());
        return true;
    }

    private String getIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown Host";
        }
    }
}
