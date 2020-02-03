package com.mmelo.starwars.controller;

import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.service.PlanetService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public PlanetDTO save(@Valid @RequestBody final PlanetDTO dto) {
        return service.savePlanet(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<PlanetDTO> getPlanets() {
        return service.getPlanets();
    }

    @GetMapping(path = "/client", produces = APPLICATION_JSON_VALUE)
    public List<PlanetDTO> getClientPlanets() {
        return service.getClientPlanets();
    }

    @GetMapping(path = "/{name}", produces = APPLICATION_JSON_VALUE)
    public PlanetDTO findById(@Valid @PathVariable final String name) {
        return service.getPlanetByName(name);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public PlanetDTO getPlanetById(@Valid @PathVariable final Long id) {
        return service.getPlanetById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePlanetById(@PathVariable final Long id) {
        this.service.deletePlanetById(id);
    }
}
