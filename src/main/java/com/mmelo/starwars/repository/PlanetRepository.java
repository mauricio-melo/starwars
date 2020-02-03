package com.mmelo.starwars.repository;

import com.mmelo.starwars.domain.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Optional<Planet> findByName(final String name);
}