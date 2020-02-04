package com.mmelo.starwars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetDTO {
    private Long id;
    private String name;
    private String climate;
    private String terrain;
    private int quantityFilms;
}
