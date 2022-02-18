package ru.mironov.marvelapi.domain.exception.image;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(UUID imageId) {
        super("File not found with id " + imageId);
    }
}
