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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlanetServiceTest {

    @InjectMocks
    private PlanetService service;

    @Mock
    private PlanetRepository repository;

    @Mock
    private SwapiClientService clientService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void savePLanet() {
        final int quantityFilms = 1;

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

        when(clientService.getQuantityFilmsByPlanet(anyString())).thenReturn(quantityFilms);
        when(repository.save(any())).thenReturn(planetMock);
        when(modelMapper.map(any(), any())).thenReturn(planetDTOMock);

        final PlanetDTO planetDTO = service.savePlanet(planetDTOMock);

        assertNotNull(planetDTO);
        assertEquals(planetDTO.getId(), planetMock.getId());
        assertEquals(planetDTO.getName(), planetMock.getName());
        assertEquals(planetDTO.getClimate(), planetMock.getClimate());
        assertEquals(planetDTO.getTerrain(), planetMock.getTerrain());
        assertEquals(planetDTO.getQuantityFilms(), planetMock.getQuantityFilms());
        verify(repository, times(1)).save(any());
        verify(modelMapper, times(1)).map(any(), any());
        verify(clientService, times(1)).getQuantityFilmsByPlanet(anyString());
    }

    @Test
    public void getPlanets() {
        final List<Planet> planetsMock = getPlanetList();
        when(repository.findAll()).thenReturn(planetsMock);
        when(modelMapper.map(any(), any())).thenReturn(new PlanetDTO());

        final List<PlanetDTO> planetDTOS = service.getPlanets();

        assertFalse(planetDTOS.isEmpty());
        verify(modelMapper, times(1)).map(any(), any());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getClientPlanets() {
        final List<PlanetDTO> planetsDTOMock = getPlanetDTOList();
        when(clientService.getPlanets()).thenReturn(planetsDTOMock);

        final List<PlanetDTO> planetDTOS = service.getClientPlanets();

        assertFalse(planetDTOS.isEmpty());
        assertEquals(planetDTOS.get(0).getId(), planetsDTOMock.get(0).getId());
        assertEquals(planetDTOS.get(0).getName(), planetsDTOMock.get(0).getName());
        assertEquals(planetDTOS.get(0).getClimate(), planetsDTOMock.get(0).getClimate());
        assertEquals(planetDTOS.get(0).getTerrain(), planetsDTOMock.get(0).getTerrain());
        assertEquals(planetDTOS.get(0).getQuantityFilms(), planetsDTOMock.get(0).getQuantityFilms());
        verify(clientService, times(1)).getPlanets();
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

    private List<Planet> getPlanetList() {
        return Collections.singletonList(Planet.builder()
                .id(1L)
                .name("Alderaan")
                .climate("temperate")
                .terrain("grasslands")
                .quantityFilms(1)
                .build());
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
