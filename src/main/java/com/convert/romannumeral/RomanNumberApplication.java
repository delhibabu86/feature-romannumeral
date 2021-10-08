package com.convert.romannumeral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RomanNumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(RomanNumberApplication.class, args);
    }

}
