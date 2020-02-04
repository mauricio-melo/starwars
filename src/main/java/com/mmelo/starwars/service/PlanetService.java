package com.mmelo.starwars.service;

import com.mmelo.starwars.domain.Planet;
import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.exception.ResourceNotFoundException;
import com.mmelo.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository repository;
    private final SwapiClientService clientService;
    private final ModelMapper modelMapper;

    public PlanetDTO savePlanet(final PlanetDTO dto) {
        final int quantityFilms = clientService.getQuantityFilmsByPlanet(dto.getName());
        final Planet planet = repository.save(Planet.builder()
                .name(dto.getName())
                .climate(dto.getClimate())
                .terrain(dto.getTerrain())
                .quantityFilms(quantityFilms)
                .build());
        return modelMapper.map(planet, PlanetDTO.class);
    }

    public List<PlanetDTO> getPlanets() {
        return repository.findAll().stream()
                .map(planet -> modelMapper.map(planet, PlanetDTO.class))
                .collect(Collectors.toList());
    }

    public List<PlanetDTO> getClientPlanets() {
        return clientService.getPlanets();
    }

    public PlanetDTO getPlanetByName(final String name) {
        return modelMapper.map(repository.findByName(name)
                .orElseThrow(ResourceNotFoundException::new), PlanetDTO.class);
    }

    public PlanetDTO getPlanetById(final Long id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new), PlanetDTO.class);
    }

    public void deletePlanetById(final Long id) {
        repository.deleteById(id);
    }

}
