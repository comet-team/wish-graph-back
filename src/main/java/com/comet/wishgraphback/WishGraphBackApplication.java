package com.comet.wishgraphback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WishGraphBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishGraphBackApplication.class, args);
    }

}
