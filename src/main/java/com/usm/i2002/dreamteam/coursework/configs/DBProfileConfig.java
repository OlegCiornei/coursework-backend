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
        return "DB connection dev";
    }

    @Bean
    @Profile("local")
    public String localDatabaseConnection() {
        return "DB connection local";
    }
}
