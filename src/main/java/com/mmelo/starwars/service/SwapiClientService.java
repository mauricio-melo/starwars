package com.mmelo.starwars.service;

import com.mmelo.starwars.config.HeaderSwapi;
import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.dto.PlanetsSwapiDTO;
import com.mmelo.starwars.exception.ResourceNotFoundException;
import com.mmelo.starwars.exception.SwapiInternalError;
import com.mmelo.starwars.integration.SwapiClient;
import com.mmelo.starwars.mapper.PlanetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwapiClientService {

    private final SwapiClient client;
    private final HeaderSwapi header;
    private final PlanetMapper mapper;

    public List<PlanetDTO> getPlanets() {
        final ResponseEntity<PlanetsSwapiDTO> dto = client.getAllPlanets(header.getUserAgent());
        validateResponseSwapi(dto);
        return mapper.getPlanetDTOByPlanetsSwapiDTO(dto.getBody());
    }

    public int getQuantityFilmsByPlanet(final String name) {
        final ResponseEntity<PlanetsSwapiDTO> dto = client.getPlanetByName(header.getUserAgent(), name);
        validateResponseSwapi(dto);
        validateExistsPlanetByName(dto);
        return dto.getBody().getItemDTO().stream()
                .findFirst()
                .map(planetSwapiItemDTO -> planetSwapiItemDTO.getFilms().size()).get();
    }

    private void validateExistsPlanetByName(final ResponseEntity<PlanetsSwapiDTO> dto) {
        if(dto.getBody().getItemDTO().isEmpty()){
            throw new ResourceNotFoundException();
        }
    }

    private void validateResponseSwapi(final ResponseEntity<PlanetsSwapiDTO> dto) {
        if(dto.getBody() == null || !dto.getStatusCode().is2xxSuccessful()) {
            throw new SwapiInternalError();
        }
    }

}
