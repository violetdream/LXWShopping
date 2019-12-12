package com.personal.shopping.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.personal.shopping.dal")
@ComponentScan(basePackages = "com.personal.shopping")

@SpringBootApplication
public class ShoppingProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingProviderApplication.class, args);
    }

}
