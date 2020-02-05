package com.mmelo.starwars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmelo.starwars.dto.PlanetDTO;
import com.mmelo.starwars.dto.request.PlanetRequestDTO;
import com.mmelo.starwars.service.PlanetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static com.mmelo.starwars.controller.PlanetController.PLANET_ENDPOINT;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PlanetControllerTests {

    @InjectMocks
    private PlanetController controller;

    @Mock
    private PlanetService service;

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    public void savePlanet() throws Exception {
        when(service.savePlanet(any())).thenReturn(getPlanetDTOList().get(0));
        mockMvc.perform(MockMvcRequestBuilders.post(PLANET_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getPlanetRequestDTO())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void getPlanets() throws Exception {
        when(service.getPlanets()).thenReturn(getPlanetDTOList());
        mockMvc.perform(MockMvcRequestBuilders.get(PLANET_ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void getClientPlanets() throws Exception {
        when(service.getClientPlanets()).thenReturn(getPlanetDTOList());
        mockMvc.perform(MockMvcRequestBuilders.get(PLANET_ENDPOINT+ "/client"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void getPlanetByName() throws Exception {
        when(service.getPlanetByName(anyString())).thenReturn(getPlanetDTOList().get(0));
        mockMvc.perform(MockMvcRequestBuilders.get(PLANET_ENDPOINT+ "/name/{name}", "Alderaan"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void getPlanetById() throws Exception {
        when(service.getPlanetById(anyLong())).thenReturn(getPlanetDTOList().get(0));
        mockMvc.perform(MockMvcRequestBuilders.get(PLANET_ENDPOINT+ "/{id}", 1L))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void deletePlanetById() throws Exception {
        PlanetService spy = Mockito.spy(service);
        doNothing().when(spy).deletePlanetById(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete(PLANET_ENDPOINT+ "/{id}", 1L))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
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

    private PlanetRequestDTO getPlanetRequestDTO() {
        return PlanetRequestDTO.builder()
                .name("Alderaan")
                .climate("temperate")
                .terrain("grasslands")
                .build();
    }
}
