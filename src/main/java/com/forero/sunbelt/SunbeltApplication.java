package com.forero.sunbelt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.forero.sunbelt")
public class SunbeltApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunbeltApplication.class, args);
    }

}
