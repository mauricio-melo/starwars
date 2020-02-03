package com.mmelo.starwars.service;

import com.mmelo.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository repository;
    private final SwapiClientService clientService;


}
