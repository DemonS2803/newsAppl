package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class NewsApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NewsApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8000"));
        app.run();

    }
}
