package ru.mironov.marvelapi.domain.exception.image;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
public class ImageTypeException extends RuntimeException {

    public ImageTypeException() {
        super("File must be in \"image/jpeg\" format");
    }
}
