package com.mmelo.starwars.domain;

import com.mmelo.starwars.commons.Auditable;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "planet")
public class Planet extends Auditable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_planet")
    private Long id;

    @Column(name = "name_planet", unique = true)
    private String name;

    @Column(name = "climate")
    private String climate;

    @Column(name = "terrain")
    private String terrain;

    @Column(name = "quantity_films")
    private Long quantityFilms;
}
