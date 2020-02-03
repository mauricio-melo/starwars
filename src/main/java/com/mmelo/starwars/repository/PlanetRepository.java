package com.mmelo.starwars.repository;

import com.mmelo.starwars.domain.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
}