package com.mmelo.starwars.integration;

import com.mmelo.starwars.dto.integration.PlanetsSwapiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "SwapiClient", url = "${integration.swapi-client.base-url}")
public interface SwapiClient {

    @GetMapping(path = "/planets", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PlanetsSwapiDTO> getAllPlanets(@RequestHeader("user-agent") final String header);

    @GetMapping(path = "/planets", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PlanetsSwapiDTO> getPlanetByName(@RequestHeader("user-agent") final String header,
                                                    @RequestParam("search") final String name);
}