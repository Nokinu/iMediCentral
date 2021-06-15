package com.czhang.service.medicenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.czhang.model.model"})
@ComponentScan(basePackages = "com.czhang")
public class ServiceMediCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMediCenterApplication.class, args);
    }
}
