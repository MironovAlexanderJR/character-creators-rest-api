package ru.mironov.marvelapi.domain.exception.creator;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author mironovAlexanderJR
 * @since 02.02.2022
 */
@ResponseStatus(value = NOT_FOUND)
public class CreatorNotFoundException extends RuntimeException {
    public CreatorNotFoundException(Long creatorId) {
        super("Comic with id: " + creatorId + " not found");
    }
}
