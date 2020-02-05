package com.mmelo.starwars.service;

import com.mmelo.starwars.config.HeaderSwapi;
import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.dto.integration.PlanetSwapiItemDTO;
import com.mmelo.starwars.dto.integration.PlanetsSwapiDTO;
import com.mmelo.starwars.exception.ResourceNotFoundException;
import com.mmelo.starwars.exception.SwapiInternalError;
import com.mmelo.starwars.integration.SwapiClient;
import com.mmelo.starwars.mapper.PlanetMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class SwapiClientServiceTest {

    @InjectMocks
    private SwapiClientService service;

    @Mock
    private SwapiClient client;

    @Mock
    private HeaderSwapi header;

    @Mock
    private PlanetMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlanetsWithClientReturn2xx() {
        final ResponseEntity<PlanetsSwapiDTO> planetsSwapiDTO = getResponseEntity2xx();
        final List<PlanetDTO> planetsDTOMock = getPlanetDTOList();
        final String userAgent = "userAgent";

        when(client.getAllPlanets(anyString())).thenReturn(planetsSwapiDTO);
        when(mapper.getPlanetDTOByPlanetsSwapiDTO(any())).thenReturn(planetsDTOMock);
        when(header.getUserAgent()).thenReturn(userAgent);

        final List<PlanetDTO> planetDTOS = service.getPlanets();

        assertFalse(planetDTOS.isEmpty());
        assertEquals(planetDTOS.get(0).getName(), planetsSwapiDTO.getBody().getItemDTO().get(0).getName());
        assertEquals(planetDTOS.get(0).getClimate(), planetsSwapiDTO.getBody().getItemDTO().get(0).getClimate());
        assertEquals(planetDTOS.get(0).getTerrain(), planetsSwapiDTO.getBody().getItemDTO().get(0).getTerrain());
        assertEquals(planetDTOS.get(0).getQuantityFilms(), planetsSwapiDTO.getBody().getItemDTO().get(0).getFilms().size());
    }

    @Test
    public void getPlanetsWithClientReturn4xx() {
        final ResponseEntity<PlanetsSwapiDTO> planetsSwapiDTO = getResponseEntity4xx();
        final List<PlanetDTO> planetsDTOMock = getPlanetDTOList();
        final String userAgent = "userAgent";

        when(client.getAllPlanets(anyString())).thenReturn(planetsSwapiDTO);
        when(mapper.getPlanetDTOByPlanetsSwapiDTO(any())).thenReturn(planetsDTOMock);
        when(header.getUserAgent()).thenReturn(userAgent);

        assertThrows(SwapiInternalError.class, () -> service.getPlanets());
    }

    @Test
    public void getQuantityFilmsByPlanet2xx() {
        final ResponseEntity<PlanetsSwapiDTO> planetsSwapiDTO = getResponseEntity2xx();
        final String userAgent = "userAgent";
        final String name = "Alderaan";

        when(client.getPlanetByName(anyString(), anyString())).thenReturn(planetsSwapiDTO);
        when(header.getUserAgent()).thenReturn(userAgent);

        final int quantityFilms = service.getQuantityFilmsByPlanet(name);

        assertEquals(1, quantityFilms);
    }

    @Test
    public void getQuantityFilmsByPlanet4xx() {
        final ResponseEntity<PlanetsSwapiDTO> planetsSwapiDTO = getResponseEntity4xx();
        final String userAgent = "userAgent";
        final String name = "Alderaan";

        when(client.getPlanetByName(anyString(), anyString())).thenReturn(planetsSwapiDTO);
        when(header.getUserAgent()).thenReturn(userAgent);

        assertThrows(SwapiInternalError.class, () -> service.getQuantityFilmsByPlanet(name));
    }

    @Test
    public void getQuantityFilmsByPlanet2xxEmpty() {
        final ResponseEntity<PlanetsSwapiDTO> planetsSwapiDTO = getResponseEntity2xx();
        planetsSwapiDTO.getBody().getItemDTO().remove(0);
        final String userAgent = "userAgent";
        final String name = "Alderaan";

        when(client.getPlanetByName(anyString(), anyString())).thenReturn(planetsSwapiDTO);
        when(header.getUserAgent()).thenReturn(userAgent);

        assertThrows(ResourceNotFoundException.class, () -> service.getQuantityFilmsByPlanet(name));
    }

    public ResponseEntity<PlanetsSwapiDTO> getResponseEntity2xx() {
        return ResponseEntity.ok(getPlanetsSwapiDTO());
    }

    public ResponseEntity<PlanetsSwapiDTO> getResponseEntity4xx() {
        final HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(
                getPlanetsSwapiDTO(),
                header,
                HttpStatus.BAD_REQUEST
        );
    }

    public PlanetsSwapiDTO getPlanetsSwapiDTO() {
        return PlanetsSwapiDTO.builder()
                .itemDTO(new ArrayList<>(Collections.singletonList(PlanetSwapiItemDTO.builder()
                        .name("Alderaan")
                        .climate("temperate")
                        .terrain("grasslands")
                        .films(Collections.singletonList("filme1"))
                        .build())))
                .build();
    }


    private List<PlanetDTO> getPlanetDTOList() {
        return Collections.singletonList(PlanetDTO.builder()
                .id(1L)
                .name("Alderaan")
                .climate("temperate")
                .terrain("grasslands")
                .quantityFilms(1)
                .build());
    }

}
