package com.mmelo.starwars.service;

import com.mmelo.starwars.domain.Planet;
import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlanetServiceTest {

    @InjectMocks
    private PlanetService service;

    @Mock
    private PlanetRepository repository;

    @Mock
    private SwapiClientService swapiClientService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlanets() {
        final List<Planet> planetsMock = getPlanetList(1);
        when(repository.findAll()).thenReturn(planetsMock);
        when(modelMapper.map(any(), any())).thenReturn(new PlanetDTO());

        List<PlanetDTO> planetDTOS = service.getPlanets();

        assertFalse(planetDTOS.isEmpty());
        verify(modelMapper, times(1)).map(any(), any());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getClientPlanets() {
        final List<PlanetDTO> planetsDTOMock = getPlanetDTOList(getPlanetList(1));
        when(swapiClientService.getPlanets()).thenReturn(planetsDTOMock);

        final List<PlanetDTO> planetDTOS = service.getClientPlanets();

        assertFalse(planetDTOS.isEmpty());
        assertEquals(planetDTOS.get(0).getId(), planetsDTOMock.get(0).getId());
        assertEquals(planetDTOS.get(0).getName(), planetsDTOMock.get(0).getName());
        assertEquals(planetDTOS.get(0).getClimate(), planetsDTOMock.get(0).getClimate());
        assertEquals(planetDTOS.get(0).getTerrain(), planetsDTOMock.get(0).getTerrain());
        assertEquals(planetDTOS.get(0).getQuantityFilms(), planetsDTOMock.get(0).getQuantityFilms());
        verify(swapiClientService, times(1)).getPlanets();
    }

    @Test
    public void getPlanetByName() {
        final Planet planetMock = Planet.builder()
                .id(1L)
                .name("Alderaan")
                .climate("temperate")
                .terrain("grasslands")
                .build();

        final PlanetDTO planetDTOMock = PlanetDTO.builder()
                .id(1L)
                .name("Alderaan")
                .climate("temperate")
                .terrain("grasslands")
                .build();

        when(repository.findByName(anyString())).thenReturn(Optional.of(planetMock));
        when(modelMapper.map(any(), any())).thenReturn(planetDTOMock);

        final PlanetDTO planetDTO = service.getPlanetByName("Alderaan");

        assertNotNull(planetDTO);
        assertEquals(planetDTO.getId(), planetMock.getId());
        assertEquals(planetDTO.getName(), planetMock.getName());
        assertEquals(planetDTO.getClimate(), planetMock.getClimate());
        assertEquals(planetDTO.getTerrain(), planetMock.getTerrain());
        assertEquals(planetDTO.getQuantityFilms(), planetMock.getQuantityFilms());
        verify(repository, times(1)).findByName(anyString());
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    public void getPlanetById() {
        final Planet planetMock = Planet.builder()
                .id(1L)
                .name("Alderaan")
                .climate("temperate")
                .terrain("grasslands")
                .build();

        final PlanetDTO planetDTOMock = PlanetDTO.builder()
                .id(1L)
                .name("Alderaan")
                .climate("temperate")
                .terrain("grasslands")
                .build();

        when(repository.findById(anyLong())).thenReturn(Optional.of(planetMock));
        when(modelMapper.map(any(), any())).thenReturn(planetDTOMock);

        final PlanetDTO planetDTO = service.getPlanetById(1L);

        assertNotNull(planetDTO);
        assertEquals(planetDTO.getId(), planetMock.getId());
        assertEquals(planetDTO.getName(), planetMock.getName());
        assertEquals(planetDTO.getClimate(), planetMock.getClimate());
        assertEquals(planetDTO.getTerrain(), planetMock.getTerrain());
        assertEquals(planetDTO.getQuantityFilms(), planetMock.getQuantityFilms());
        verify(repository, times(1)).findById(anyLong());
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    public void deletePlanetById() {
        PlanetService spy = Mockito.spy(service);
        doNothing().when(spy).deletePlanetById(anyLong());

        service.deletePlanetById(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    private List<Planet> getPlanetList(final int size) {
        final List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            planets.add(Planet.builder()
                    .id((long) i)
                    .name("Alderaan" + i)
                    .climate("temperate")
                    .terrain("grasslands")
                    .quantityFilms(i)
                    .build());
        }
        return planets;
    }

    private List<PlanetDTO> getPlanetDTOList(final List<Planet> planet) {
        return planet.stream()
                .map(item -> PlanetDTO.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .climate(item.getClimate())
                        .terrain(item.getTerrain())
                        .quantityFilms(item.getQuantityFilms())
                        .build())
                .collect(Collectors.toList());
    }
}
