package com.mmelo.starwars.dto;

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
public class PlanetsSwapiDTO {

    @JsonProperty("results")
    private List<PlanetSwapiItemDTO> itemDTO;
}
