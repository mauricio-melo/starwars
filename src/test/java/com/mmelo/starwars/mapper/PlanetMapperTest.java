package com.mmelo.starwars.mapper;

import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.dto.integration.PlanetSwapiItemDTO;
import com.mmelo.starwars.dto.integration.PlanetsSwapiDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanetMapperTest {

    @InjectMocks
    private PlanetMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlanetDTOByPlanetsSwapiDTO() {
        final PlanetsSwapiDTO planetsSwapiDTO = PlanetsSwapiDTO.builder()
                .itemDTO(Collections.singletonList(PlanetSwapiItemDTO.builder()
                        .name("Alderaan")
                        .climate("temperate")
                        .terrain("grasslands")
                        .films(Collections.singletonList("filme1"))
                        .build()))
                .build();

        final List<PlanetDTO> planetsDTO = mapper.getPlanetDTOByPlanetsSwapiDTO(planetsSwapiDTO);

        assertEquals(planetsSwapiDTO.getItemDTO().get(0).getName(), planetsDTO.get(0).getName());
        assertEquals(planetsSwapiDTO.getItemDTO().get(0).getClimate(), planetsDTO.get(0).getClimate());
        assertEquals(planetsSwapiDTO.getItemDTO().get(0).getTerrain(), planetsDTO.get(0).getTerrain());
        assertEquals(planetsSwapiDTO.getItemDTO().get(0).getFilms().size(), planetsDTO.get(0).getQuantityFilms());
    }
}
