package com.mmelo.starwars.controller;

import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.dto.request.PlanetRequestDTO;
import com.mmelo.starwars.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(PlanetController.PLANET_ENDPOINT)
public class PlanetController {

    public static final String PLANET_ENDPOINT = "/planet";
    private final PlanetService service;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public PlanetDTO savePlanet(@Valid @RequestBody final PlanetRequestDTO dto) {
        return service.savePlanet(modelMapper.map(dto, PlanetDTO.class));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<PlanetDTO> getPlanets() {
        return service.getPlanets();
    }

    @GetMapping(path = "/client", produces = APPLICATION_JSON_VALUE)
    public List<PlanetDTO> getClientPlanets() {
        return service.getClientPlanets();
    }

    @GetMapping(path = "/name/{name}", produces = APPLICATION_JSON_VALUE)
    public PlanetDTO getPlanetByName(@Valid @PathVariable final String name) {
        return service.getPlanetByName(name);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public PlanetDTO getPlanetById(@Valid @PathVariable final Long id) {
        return service.getPlanetById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePlanetById(@PathVariable final Long id) {
        service.deletePlanetById(id);
    }
}
