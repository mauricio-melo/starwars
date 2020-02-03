package com.mmelo.starwars.service;

import com.mmelo.starwars.dto.PlanetSwapiDTO;
import com.mmelo.starwars.integration.SwapiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwapiClientService {

    private final SwapiClient client;

    public PlanetSwapiDTO getPlanets(){
        return client.getPlanets();
    }

}
