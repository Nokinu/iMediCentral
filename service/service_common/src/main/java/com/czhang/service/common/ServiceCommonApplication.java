package com.czhang.service.common;

import com.github.xiaolyuh.cache.config.EnableLayeringCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.czhang.model.model"})
@ComponentScan(basePackages = "com.czhang")
@EnableLayeringCache
public class ServiceCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCommonApplication.class, args);
    }

}
