package com.usm.i2002.dreamteam.coursework.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBProfileConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    @Profile("dev")
    public String devDatabaseConnection() {
        System.out.println("Using dev profile");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection dev";
    }

    @Bean
    @Profile("local")
    public String localDatabaseConnection() {
        System.out.println("Using dev profile");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection local";
    }
}
