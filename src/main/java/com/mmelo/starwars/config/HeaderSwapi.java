package com.mmelo.starwars.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("integration.swapi-client")
public class HeaderSwapi {
    private String userAgent;
}