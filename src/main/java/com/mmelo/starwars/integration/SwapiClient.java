package com.mmelo.starwars.integration;

import com.mmelo.starwars.dto.PlanetSwapiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "SwapiClient", url = "${integration.swapi-client.base-url}")
public interface SwapiClient {

    @GetMapping(path = "/planets", produces = APPLICATION_JSON_VALUE)
    PlanetSwapiDTO getPlanets();
}