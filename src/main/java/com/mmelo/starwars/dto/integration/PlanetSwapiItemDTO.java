package com.mmelo.starwars.dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetSwapiItemDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("climate")
    private String climate;

    @JsonProperty("terrain")
    private String terrain;

    @JsonProperty("films")
    private List<String> films;
}
