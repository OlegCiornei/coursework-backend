package com.usm.i2002.dreamteam.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseworkApplication {

    public static void main(String[] args) {
        System.setProperty("current.date", String.valueOf(System.currentTimeMillis()));
        SpringApplication.run(CourseworkApplication.class, args);
    }

}