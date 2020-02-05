package com.mmelo.starwars.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StarWarsMessage {

    SERVER_ERROR_SWAPI("An unexpected error has occurred in SWAPI. Please try again"),

    SERVER_ERROR("An unexpected error has occurred. Please try again"),

    RESOURCE_NOT_FOUND("Requested resource could not be found"),

    RESOURCE_ALREADY_EXISTS("Resource already exists");

    private final String message;
}
