package ru.mironov.marvelapi.domain.exception.comic;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author mironovAlexanderJR
 * @since 02.02.2022
 */
@ResponseStatus(value = NOT_FOUND)
public class ComicNotFoundException extends RuntimeException {
    public ComicNotFoundException(UUID comicId) {
        super("Comic with id: " + comicId + " not found");
    }
}
