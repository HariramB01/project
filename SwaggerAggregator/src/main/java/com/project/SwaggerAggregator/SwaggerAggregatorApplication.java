package com.project.SwaggerAggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients(basePackages = "com.project.FeignClient")
public class SwaggerAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerAggregatorApplication.class, args);
	}
}
