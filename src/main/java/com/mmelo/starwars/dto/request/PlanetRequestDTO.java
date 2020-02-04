package com.mmelo.starwars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetRequestDTO {
    private String name;
    private String climate;
    private String terrain;
}
