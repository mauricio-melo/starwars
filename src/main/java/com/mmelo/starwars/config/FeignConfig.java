package com.mmelo.starwars.config;

import com.mmelo.starwars.integration.SwapiClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {SwapiClient.class})
public class FeignConfig {

}