package ru.mironov.marvelapi.domain.exception.character;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author mironovAlexanderJR
 * @since 02.02.2022
 */
@ResponseStatus(value = NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(Long characterId) {
        super("Character with id: " + characterId + " not found");
    }
}
