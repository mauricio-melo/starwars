package com.mmelo.starwars.mapper;

import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.dto.integration.PlanetsSwapiDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanetMapper {

    public List<PlanetDTO> getPlanetDTOByPlanetsSwapiDTO(final PlanetsSwapiDTO dto) {
        return dto.getItemDTO().stream()
                .map(item -> PlanetDTO.builder()
                        .name(item.getName())
                        .climate(item.getClimate())
                        .terrain(item.getTerrain())
                        .quantityFilms(item.getFilms().size())
                        .build())
                .collect(Collectors.toList());
    }
}
